package getcooked.gui;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenCreator {
    private int width;
    private int height;
    private final AWTTerminalFontConfiguration fontConfig;
    private Terminal terminal;

    public ScreenCreator(AWTTerminalFontConfiguration fontConfig, int fontSize) {
        this.fontConfig = fontConfig;
        setTerminalSizes(fontSize);
    }

    private void setTerminalSizes(int fontSize) {
        // usable size of the screen
        Rectangle terminalBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.width = (terminalBounds.width / fontSize);
        this.height = (terminalBounds.height / fontSize);
    }

    private Terminal createTerminal(AWTTerminalFontConfiguration fontConfig) throws IOException{
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        terminalFactory.setTerminalEmulatorTitle("Get Cooked");
        return terminalFactory.createTerminal();
    }

    public Screen createScreen() throws IOException {
        this.terminal = createTerminal(fontConfig);

        Screen screen = new TerminalScreen(terminal);
        try {
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            return screen;
        } catch (IOException e) {
            Logger.getLogger(ScreenCreator.class.getName()).log(Level.SEVERE, "Screen creation failed", e);
            screen.close();
        }
        return screen;
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
