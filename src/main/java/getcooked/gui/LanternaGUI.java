package getcooked.gui;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import getcooked.model.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class LanternaGUI implements GUI {
    private final Screen screen;
    private final InputManager inputManager;
    private final FontManager fontManager;
    private String backgroundColor; // to remember current background color

    public LanternaGUI() throws IOException, FontFormatException, URISyntaxException{
        this.fontManager = new FontManager();
        AWTTerminalFontConfiguration fontConfig = fontManager.loadPixelFont();
        ScreenCreator screenCreator = new ScreenCreator(fontConfig, fontManager.getFontSize());
        this.screen = screenCreator.createScreen();
        this.inputManager = new InputManager(screenCreator.getTerminal());
        this.backgroundColor = "#ffffff";
    }

    public LanternaGUI(ScreenCreator screenCreator, FontManager fontManager, InputManager inputManager)
            throws IOException {
        this.screen = screenCreator.createScreen();
        this.inputManager = inputManager;
        this.fontManager = fontManager;
        this.backgroundColor = "#ffffff";
    }

    @Override
    public int getHeight() {
        return screen.getTerminalSize().getRows();
    }

    @Override
    public int getWidth() {
        return screen.getTerminalSize().getColumns();
    }

    @Override
    public Set<GUI.ACTION> getNextAction() {
        return inputManager.getNextAction();
    }

    @Override
    public void drawText(Position position, String text, String color) {
        int curX = position.x();
        int y = position.y();
        for (int i = 0; i < text.length(); i++) {
            char curChar = text.charAt(i);
            drawCharacter(curChar, curX, y, color);

            curX += 4; // pixel font has 3px of width
        }
    }

    private void drawCharacter(char c, int x, int y, String color) {
        // Use cached resources instead of loading every time
        Map<Character, Coordinate> cache = fontManager.getCharPositionCache();
        BufferedImage fontImage = fontManager.getFontImage();

        Coordinate coordinate = cache.get(c);
        if (coordinate == null) return;  // Character not found in font map

        int imgX = coordinate.x();
        int imgY = coordinate.y();

        for (int dx = 0; dx < 3; dx++) {
            for (int dy = 0; dy < 5; dy++) {
                if (fontImage.getRGB(imgX + dx, imgY + dy) != 0xFFFFFFFF)
                    drawPixel(x + dx, y + dy, TextColor.Factory.fromString(color));
            }
        }
    }

    @Override
    public void drawRectangle(Position position, int width, int height, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(color));

        tg.fillRectangle(new TerminalPosition(position.x(),position.y()),
                new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawPixel(int x, int y, TextColor color){
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.setCharacter(x, y, ' ');
    }

    @Override
    public void changeBackgroundColor(String newColor) {
        this.backgroundColor = newColor;
    }

    @Override
    public String getBackgroundColor() {
        return this.backgroundColor;
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
        screen.refresh(Screen.RefreshType.DELTA);
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
