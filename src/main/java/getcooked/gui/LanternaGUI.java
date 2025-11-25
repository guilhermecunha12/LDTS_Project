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
import java.util.HashSet;
import java.util.Set;

public class LanternaGUI implements GUI{

    private final Screen screen;
    private final Set<Integer> activeInputs;

    public LanternaGUI(Screen screen){
        this.screen = screen;
        this.activeInputs = new HashSet<>();
    };

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException{
        AWTTerminalFontConfiguration fontConfig = loadSegoeFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        // Consider synchronized set if needed
        this.activeInputs = new HashSet<>();

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

    private AWTTerminalFontConfiguration loadSegoeFont() throws URISyntaxException, FontFormatException, IOException{
        URL resource = getClass().getClassLoader().getResource("fonts/segoe.ttf");
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

        return actions;
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, "" + c);
    }

    @Override
    public void drawChef1(Position position) {
        drawCharacter(position.getX(), position.getY(), 'C', "#9b7bc7");
    }

    @Override
    public void drawChef2(Position position) {
        drawCharacter(position.getX(), position.getY(), 'C', "#d5e6f2");
    }

    @Override
    //not correct implementation
    public void drawOrder(Position position) {
        drawCharacter(position.getX(), position.getY(), 'C', "#FFD700");
    }

    @Override
    public void drawTrash(Position position){
        drawCharacter(position.getX(), position.getY(), 'T', "#c0c0c0");
    }

    @Override
    public void drawDelivery(Position position) {
        drawCharacter(position.getX(), position.getY(), 'D', "#ffd700");
    }

    @Override
    public void drawCounter(Position position){
        drawCharacter(position.getX(), position.getY(), '#', "#eeeeee");
    }

    @Override
    public void drawSodaDispenser(Position position) {
        drawCharacter(position.getX(), position.getY(), 'S', "#FF0000");
    }

    @Override
    public void drawKebabDispenser(Position position){
        drawCharacter(position.getX(), position.getY(), 'K', "#FF0000");
    }

    @Override
    public void drawSaladDispenser(Position position){
        drawCharacter(position.getX(), position.getY(), 'S', "#FF0000");
    }

    @Override
    public void drawCarrotDispenser(Position position){
        drawCharacter(position.getX(), position.getY(), 'C', "#FF0000");
    }

    @Override
    public void drawPlateDispenser(Position position){
        drawCharacter(position.getX(), position.getY(), 'P', "#FF0000");
    }

    @Override
    public void drawPotatoDispenser(Position position){
        drawCharacter(position.getX(), position.getY(), 'p', "#FF0000");
    }

    @Override
    public void drawRawBeefDispenser(Position position){
        drawCharacter(position.getX(), position.getY(), 'B', "#FF0000");
    }

    @Override
    public void drawSoda(Position position){
        drawCharacter(position.getX(), position.getY(), 'S', "#420420");
    }

    @Override
    public void drawKebab(Position position){
        drawCharacter(position.getX(), position.getY(), 'K', "#cc0000");
    }

    @Override
    public void drawSalad(Position position){
        drawCharacter(position.getX(), position.getY(), 'S', "#008000");
    }

    @Override
    public void drawCarrot(Position position){
        drawCharacter(position.getX(), position.getY(), 'C', "#ffa500");
    }

    @Override
    public void drawChoppedCarrot(Position position){
        drawCharacter(position.getX(), position.getY(), 'c', "#ffa500");
    }

    @Override
    public void drawPlate(Position position){
        drawCharacter(position.getX(), position.getY(), 'O', "#Fffff");
    }

    @Override
    public void drawPotato(Position position){
        drawCharacter(position.getX(), position.getY(), 'P', "#daa520");
    }

    @Override
    public void drawChoppedPotato(Position position){
        drawCharacter(position.getX(), position.getY(), 'P', "#daa520");
    }

    @Override
    public void drawFriedPotato(Position position){
        drawCharacter(position.getX(), position.getY(), 'F', "#fff68f");
    }

    @Override
    public void drawBurntPotato(Position position){
        drawCharacter(position.getX(), position.getY(), 'P', "#333333");
    }

    @Override
    public void drawRawBeef(Position position){
        drawCharacter(position.getX(), position.getY(), 'B', "#800000");
    }

    @Override
    public void drawGrilledBeef(Position position){
        drawCharacter(position.getX(), position.getY(), 'B', "#faebd7");
    }

    @Override
    public void drawBurntBeef(Position position){
        drawCharacter(position.getX(), position.getY(), 'B', "#333333");
    }

    @Override
    public void drawFryer(Position position){
        drawCharacter(position.getX(), position.getY(), 'F', "#666666");
    }

    @Override
    public void drawPot(Position position){
        drawCharacter(position.getX(), position.getY(), 'Q', "#666666");
    }

    @Override
    public void drawGrill(Position position){
        drawCharacter(position.getX(), position.getY(), 'G', "#666666");
    }

    @Override
    public void drawCutter(Position position){
        drawCharacter(position.getX(), position.getY(), 'C', "#666666");
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawTextBox(Screen screen, Position position, String text,
                            String fgColor, String bgColor, int padding) {
        TextGraphics tg = screen.newTextGraphics();

        int width = text.length() + 2 * padding;
        int height = 1 + 2 * padding;

        tg.setBackgroundColor(TextColor.Factory.fromString(bgColor));

        // Check if it's drawing the textbox
        tg.fillRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(width, height), ' ');

        drawText(position, text, fgColor);
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

