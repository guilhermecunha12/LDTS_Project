package getcooked.gui;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
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
import java.util.HashSet;
import java.util.Set;

public class LanternaGUI implements GUI {

    private final int height; // for now, final
    private final int width; // for now, final
    private final Screen screen;
    private final Set<Integer> activeInputs;
    private String backgroundColor; // to remember current background color

    public LanternaGUI(Screen screen){
        this.screen = screen;
        this.height = screen.getTerminalSize().getRows();
        this.width = screen.getTerminalSize().getColumns();
        this.activeInputs = new HashSet<>();
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
        this.backgroundColor = "#ffffff";

        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                activeInputs.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                activeInputs.remove(e.getKeyCode());
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
        // Transforming a set of key codes into a set of GUI actions
        Set<GUI.ACTION> actions = new HashSet<>();
        if(activeInputs.contains(KeyEvent.VK_W)) actions.add(ACTION.PLAYER1_UP);
        if(activeInputs.contains(KeyEvent.VK_A)) actions.add(ACTION.PLAYER1_LEFT);
        if(activeInputs.contains(KeyEvent.VK_S)) actions.add(ACTION.PLAYER1_DOWN);
        if(activeInputs.contains(KeyEvent.VK_D)) actions.add(ACTION.PLAYER1_RIGHT);
        if(activeInputs.contains(KeyEvent.VK_SPACE)) actions.add(ACTION.PLAYER1_SELECT);
        if(activeInputs.contains(KeyEvent.VK_UP)) actions.add(ACTION.PLAYER2_UP);
        if(activeInputs.contains(KeyEvent.VK_LEFT)) actions.add(ACTION.PLAYER2_LEFT);
        if(activeInputs.contains(KeyEvent.VK_DOWN)) actions.add(ACTION.PLAYER2_DOWN);
        if(activeInputs.contains(KeyEvent.VK_RIGHT)) actions.add(ACTION.PLAYER2_RIGHT);
        if(activeInputs.contains(KeyEvent.VK_ENTER)) actions.add(ACTION.PLAYER2_SELECT);
        if(activeInputs.contains(KeyEvent.VK_ESCAPE)) actions.add(ACTION.ESCAPE);
        // We must clear the activeInputs set each iteration
        // Example: User pressed the down arrow in a certain frame.
        // If he doesn't release the key in time, that same key is still active in the next frame, making the action
        // appear "doubled" (it will be processed again)
        activeInputs.clear();
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

