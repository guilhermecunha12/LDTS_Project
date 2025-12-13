package getcooked.gui;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import getcooked.model.Position;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LanternaGUI implements GUI {
    private int width;
    private int height;
    private Screen screen;
    private final Set<Integer> activeInputs; // Currently pressed keys
    private final Set<Integer> previousInputs; // Pressed keys in the previous frame
    private final Map<Integer, Long> lastActionTimes; // Last action times for keys
    private static final long MIN_HOLD_TIME = 150;// Time needed to consider the keyHold a valid Hold
    private String backgroundColor;// to remember current background color

    // Static key-to-action mapping for all available keybinds
    private static final Map<Integer, GUI.ACTION> KEY_MAPPINGS = Map.ofEntries(
            // PLAYER 1
            Map.entry(KeyEvent.VK_W, GUI.ACTION.PLAYER1_UP),
            Map.entry(KeyEvent.VK_A, GUI.ACTION.PLAYER1_LEFT),
            Map.entry(KeyEvent.VK_S, GUI.ACTION.PLAYER1_DOWN),
            Map.entry(KeyEvent.VK_D, GUI.ACTION.PLAYER1_RIGHT),
            Map.entry(KeyEvent.VK_SPACE, GUI.ACTION.PLAYER1_SELECT),
            // PLAYER 2
            Map.entry(KeyEvent.VK_UP, GUI.ACTION.PLAYER2_UP),
            Map.entry(KeyEvent.VK_LEFT, GUI.ACTION.PLAYER2_LEFT),
            Map.entry(KeyEvent.VK_DOWN, GUI.ACTION.PLAYER2_DOWN),
            Map.entry(KeyEvent.VK_RIGHT, GUI.ACTION.PLAYER2_RIGHT),
            Map.entry(KeyEvent.VK_ENTER, GUI.ACTION.PLAYER2_SELECT),
            // ESCAPE
            Map.entry(KeyEvent.VK_ESCAPE, GUI.ACTION.ESCAPE)
    );


    public LanternaGUI(Screen screen){
        this.screen = screen;
        this.height = screen.getTerminalSize().getRows();
        this.width = screen.getTerminalSize().getColumns();
        this.activeInputs = new HashSet<>();
        this.previousInputs = new HashSet<>();
        this.lastActionTimes = new HashMap<>();
        this.backgroundColor = "#ffffff";
    }

    // Constructor for testing purposes
    public LanternaGUI(int height, int width, Set<Integer> activeInputs,
                       Set<Integer> previousInputs, Map<Integer, Long> lastActionTimes, String
                       backgroundColor) {
        this.height = height;
        this.width = width;
        this.activeInputs = activeInputs;
        this.previousInputs = previousInputs;
        this.lastActionTimes = lastActionTimes;
        this.backgroundColor = backgroundColor;
        this.screen = null;
    }

    public LanternaGUI() throws IOException, FontFormatException, URISyntaxException{
        Rectangle screenBounds = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .getBounds();

        AWTTerminalFontConfiguration menuFontConfig = loadPixelFont(10);

        int charWidth = menuFontConfig.getFontWidth();
        int charHeight = menuFontConfig.getFontHeight();
        int terminalWidth = (screenBounds.width) / charWidth;
        int terminalHeight = (screenBounds.height) / charHeight;
        this.width = terminalWidth - 3;
        this.height = terminalHeight - 4;
        Terminal terminal = createTerminal(width, height, menuFontConfig);
        this.screen = createScreen(terminal);
        this.activeInputs = new HashSet<>();
        this.previousInputs = new HashSet<>();
        this.lastActionTimes = new HashMap<>();
        this.backgroundColor = "#ffffff";
        addKeyListener(terminal);
    }

    private void addKeyListener(Terminal terminal){
        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                activeInputs.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                activeInputs.remove(e.getKeyCode());
                // When the key is released, wipe the last action time
                lastActionTimes.remove(e.getKeyCode());
            }
        });
    }

    private Screen createScreen(Terminal terminal) throws IOException{
        final Screen screen = new TerminalScreen(terminal);
        // Attempts to hide the cursor
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        // Checks if the terminal was resized and if so updates the internals of the screen
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException{
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadPixelFont(int fontSize) throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        // Derive the pixel font size
        Font pixelFont = font.deriveFont(Font.PLAIN, fontSize);
        return AWTTerminalFontConfiguration.newInstance(pixelFont);
    }

    @Override
    public String getBackgroundColor() {
        return backgroundColor.toString();
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public Set<GUI.ACTION> getNextAction() {
        // Start with empty set of actions
        Set<GUI.ACTION> actions = new HashSet<>();
        // Current time for the getNextAction processing

        // Processes the map iterating like a set
        for (Map.Entry<Integer, GUI.ACTION> mapping : KEY_MAPPINGS.entrySet()) {
            tryProcessKey(mapping.getKey(), mapping.getValue(), System.currentTimeMillis(), actions);
        }

        // Update previousInputs for next frame
        previousInputs.clear();
        previousInputs.addAll(activeInputs);

        return actions;
    }

    // Public for testing purposes
    public void tryProcessKey(int keyCode, GUI.ACTION action, long currentTime, Set<GUI.ACTION> actions) {
        // Key not pressed, skip processing
        if (activeInputs.contains(keyCode)) {
            // Check if this is a newly pressed key (not in previous frame)
            boolean justPressed = !previousInputs.contains(keyCode);
            if (justPressed) {
                // New key press - add action and record time
                actions.add(action);
                lastActionTimes.put(keyCode, currentTime);
            } else {
                // Key is being held - check if enough time has passed for repeat
                Long lastActionTime = lastActionTimes.get(keyCode);
                if (lastActionTime != null && currentTime - lastActionTime > MIN_HOLD_TIME) {
                    // Repeat threshold exceeded - add action and update timer
                    actions.add(action);
                    lastActionTimes.put(keyCode, currentTime);
                }
            }
        }
    }

    /*
    private void drawCharacter(Position position, char c, String color, String bgColor) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), String.valueOf(c));
    }

    @Override
    public void drawFloor(Position position) {
        changeBackgroundColor("#c4a484"); // change the gui's background color
        drawCharacter(position, ' ', "#468499", this.backgroundColor);
    }

    @Override
    public void drawChef1(Position position) {
        drawCharacter(position, 'C', "#9b7bc7", this.backgroundColor);
    }

    @Override
    public void drawChef2(Position position) {
        drawCharacter(position, 'C', "#d5e6f2", this.backgroundColor);
    }

    @Override
    //not correct implementation
    public void drawOrder(Position position) {
        drawCharacter(position, 'C', "#FFD700", this.backgroundColor);
    }

    @Override
    public void drawTrash(Position position){
        drawCharacter(position, 'T', "#c0c0c0", "#468499");
    }

    @Override
    public void drawDelivery(Position position) {
        drawCharacter(position, 'D', "#ffd700", "#468499");
    }

    @Override
    public void drawCounter(Position position){
        drawCharacter(position, '#', "#000000", "#468499");
    }

    @Override
    public void drawSodaDispenser(Position position) {
        drawCharacter(position, 'S', "#FF0000", "#468499");
    }

    @Override
    public void drawKebabDispenser(Position position){
        drawCharacter(position, 'K', "#FF0000", "#468499");
    }

    @Override
    public void drawSaladDispenser(Position position){
        drawCharacter(position, 'S', "#FF0000", "#468499");
    }

    @Override
    public void drawCarrotDispenser(Position position){
        drawCharacter(position, 'C', "#FF0000", "#468499");
    }

    @Override
    public void drawPlateDispenser(Position position){
        drawCharacter(position, 'P', "#FF0000", "#468499");
    }

    @Override
    public void drawPotatoDispenser(Position position){
        drawCharacter(position, 'p', "#FF0000", "#468499");
    }

    @Override
    public void drawRawBeefDispenser(Position position){
        drawCharacter(position, 'B', "#FF0000", "#468499");
    }

    @Override
    public void drawSoda(Position position){
        drawCharacter(position, 'S', "#420420", "#468499");
    }

    @Override
    public void drawKebab(Position position){
        drawCharacter(position, 'K', "#cc0000", "#468499");
    }

    @Override
    public void drawSalad(Position position){
        drawCharacter(position, 'S', "#008000", "#468499");
    }

    @Override
    public void drawCarrot(Position position){
        drawCharacter(position, 'C', "#ffa500","#468499");
    }

    @Override
    public void drawChoppedCarrot(Position position){
        drawCharacter(position, 'c', "#98621f","#468499");
    }

    @Override
    public void drawPlate(Position position){
        drawCharacter(position, 'O', "#ffffff", "#468499");
    }

    @Override
    public void drawPotato(Position position){
        drawCharacter(position, 'P', "#daa520", "#468499");
    }

    @Override
    public void drawChoppedPotato(Position position){
        drawCharacter(position, 'p', "#74721f", "#468499");
    }

    @Override
    public void drawFriedPotato(Position position){
        drawCharacter(position, 'F', "#fff68f", "#468499");
    }

    @Override
    public void drawBurntPotato(Position position){
        drawCharacter(position, 'P', "#333333", "#468499");
    }

    @Override
    public void drawRawBeef(Position position){
        drawCharacter(position, 'B', "#800000", "#468499");
    }

    @Override
    public void drawGrilledBeef(Position position){
        drawCharacter(position, 'B', "#faebd7","#468499");
    }

    @Override
    public void drawBurntBeef(Position position){
        drawCharacter(position, 'B', "#333333", "#468499");
    }

    @Override
    public void drawFryer(Position position){
        drawCharacter(position, 'F', "#666666","#468499");
    }

    @Override
    public void drawPot(Position position){
        drawCharacter(position, 'Q', "#666666", "#468499");
    }

    @Override
    public void drawGrill(Position position){
        drawCharacter(position, 'G', "#666666", "#468499");
    }

    @Override
    public void drawCutter(Position position){
        drawCharacter(position, 'K', "#666666", "#468499");
    }

    @Override
    public void drawPotatoSoup(Position position){ drawCharacter(position, '*', "#ffa500", "#ffffff");}

    @Override
    public void drawCarrotSoup(Position position){ drawCharacter(position, '*', "#33a500", "#ffffff");}

*/
    @Override
    public void drawText(Position position, String text, String color, String bgColor) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawTextBox(Position position, String text, String textColor, String bgColor, int padding) {
        TextGraphics tg = screen.newTextGraphics();

        int width = text.length() + 2 * padding;
        int height = 1 + 2 * padding;

        tg.setBackgroundColor(TextColor.Factory.fromString(bgColor));

        // Check if it's drawing the textbox
        tg.fillRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(width, height), ' ');

        // center the text in the box
        Position textPosition = new Position(position.getX() + padding, position.getY() + padding);
        drawText(textPosition, text, textColor, bgColor);
    }

    @Override
    public void drawRectangle(Position position, int width, int height, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(color));

        tg.fillRectangle(new TerminalPosition(position.getX(),position.getY()),
                new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawPixel(double x, double y, TextColor color){
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.setCharacter((int) x, (int) y, ' ');
    }

    @Override
    public void changeBackgroundColor(String newColor) {
        this.backgroundColor = newColor;
    }

    @Override
    public void paint() {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(this.backgroundColor));
        tg.fill(' '); // fill the whole screen with the new background color
    }

    @Override
    public void clear(){
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}