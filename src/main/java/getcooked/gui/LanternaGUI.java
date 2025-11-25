package getcooked.gui;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
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
    public Set<GUI.ACTION> getNextAction(){
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

}
