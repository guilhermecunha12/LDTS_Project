package getcooked.gui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ScreenCreatorTest {
    private ScreenCreator screenCreator;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException, FontFormatException {
        FontManager fontManager = new FontManager();
        AWTTerminalFontConfiguration config = fontManager.loadPixelFont();
        screenCreator = new ScreenCreator(config, 12);
    }

    @Test
    void createScreenReturnsNonNull() throws IOException {
        Screen screen = screenCreator.createScreen();

        assertNotNull(screen);
        assertNotNull(screenCreator.getTerminal());
    }

}