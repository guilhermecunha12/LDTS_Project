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

    private final int height; // for now, final
    private final int width; // for now, final
    private final Screen screen;
    private final Set<Integer> activeInputs; // Currently pressed keys
    private final Set<Integer> previousInputs; // Pressed keys in the previous frame
    private final Map<Integer, Long> lastActionTimes; // Last action times for keys
    private static final long MIN_HOLD_TIME = 150;// Time needed to consider the keyHold a valid Hold
    private String backgroundColor; // to remember current background color

    public LanternaGUI(Screen screen){
        this.screen = screen;
        this.height = screen.getTerminalSize().getRows();
        this.width = screen.getTerminalSize().getColumns();
        this.activeInputs = new HashSet<>();
        this.previousInputs = new HashSet<>();
        this.lastActionTimes = new HashMap<>();
        this.backgroundColor = "#ffffff";
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException{
        this.width = width;
        this.height = height;
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        // Consider synchronized set if needed
        this.activeInputs = new HashSet<>();
        this.previousInputs = new HashSet<>();
        this.lastActionTimes = new HashMap<>();
        this.backgroundColor = "#ffffff";

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


    public  Screen createScreen(Terminal terminal) throws IOException{
        final Screen screen;
        screen = new TerminalScreen(terminal);
        // Attempts to hide the cursor
        screen.setCursorPosition(null);
        screen.startScreen();
        // Checks if the terminal was resized and if so updates the internals of the screen
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException{
        TerminalSize terminalSize = new TerminalSize(width, height +1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException{
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        // Can change the font regarding appearance and size
        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
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
        Set<GUI.ACTION> actions = new HashSet<>();
        // Current time of action processing
        long currentTime = System.currentTimeMillis();

        // BIConsumer operates through side effects: for each call of processKey.accept, the function will execute
        // for that user input
        java.util.function.BiConsumer<Integer, ACTION> processKey = (keyCode, action) -> {
            // We only care about keys that are active inputs in the frame (added through the KeyPress method)
            if (activeInputs.contains(keyCode)) {
                // Just pressed checks if the current key is a new input -> it wasn't pressed in the previous
                // frame activeInputs
                boolean justPressed = !previousInputs.contains(keyCode);
                // If it's a new key that is being pressed, add them to actions and update the time it was pressed
                if (justPressed) {
                    // When a certain action occurs, the time of the action is updated
                    actions.add(action);
                    lastActionTimes.put(keyCode, currentTime);
                } else {
                    // Obtain the last time the action occurred, since it's still held
                    Long lastActionTime = lastActionTimes.get(keyCode);
                    // Ensures that even if that key is released during the execution of this function we don't have an error
                    if (lastActionTime != null) {
                        // Calculate the time between the current actionProcessTime and the last press time
                        long heldTime = currentTime - lastActionTime;
                        // If the time is greater than repeatDelay (we have a valid hold and the action wil be repeated)
                        // If the time is too short, actions will not be updated
                        if (heldTime > MIN_HOLD_TIME) {
                            // When a certain action occurs, the time of the action is updated
                            actions.add(action);
                            lastActionTimes.put(keyCode, currentTime);
                        }
                    }
                }
            }
        };

        // PLAYER 1
        processKey.accept(KeyEvent.VK_W, ACTION.PLAYER1_UP);
        processKey.accept(KeyEvent.VK_A, ACTION.PLAYER1_LEFT);
        processKey.accept(KeyEvent.VK_S, ACTION.PLAYER1_DOWN);
        processKey.accept(KeyEvent.VK_D, ACTION.PLAYER1_RIGHT);
        processKey.accept(KeyEvent.VK_SPACE, ACTION.PLAYER1_SELECT);

        // PLAYER 2
        processKey.accept(KeyEvent.VK_UP, ACTION.PLAYER2_UP);
        processKey.accept(KeyEvent.VK_LEFT, ACTION.PLAYER2_LEFT);
        processKey.accept(KeyEvent.VK_DOWN, ACTION.PLAYER2_DOWN);
        processKey.accept(KeyEvent.VK_RIGHT, ACTION.PLAYER2_RIGHT);
        processKey.accept(KeyEvent.VK_ENTER, ACTION.PLAYER2_SELECT);

        // ESCAPE
        processKey.accept(KeyEvent.VK_ESCAPE, ACTION.ESCAPE);

        // Prepare previousInputs set for next frame
        previousInputs.clear();
        // Adds all active inputs of the frame as they will become the previous inputs in the next one
        previousInputs.addAll(activeInputs);

        return actions;
    }

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
        drawCharacter(position, 'c', "#ffa500","#468499");
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
        drawCharacter(position, 'P', "#daa520", "#468499");
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
        drawCharacter(position, 'C', "#666666", "#468499");
    }

    @Override
    public void drawPotatoSoup(Position position){ drawCharacter(position, '*', "#ffa500", "#ffffff");}

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

        tg.fillRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(width, height), ' ');
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

