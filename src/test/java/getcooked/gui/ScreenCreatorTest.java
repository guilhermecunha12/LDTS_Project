package getcooked.gui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.mockito.Mockito.*;

class ScreenCreatorTest {
    private ScreenCreator screenCreator;

    @BeforeEach
    void setUp() {
        // Close any previous terminal instances
        AWTTerminalFontConfiguration fontConfig = mock(AWTTerminalFontConfiguration.class);
        when(fontConfig.getFontHeight()).thenReturn(12);
        when(fontConfig.getFontWidth()).thenReturn(12);
        screenCreator = new ScreenCreator(fontConfig, 10);
    }

    @Test
    void createScreenReturnsNonNull() throws IOException {
        Screen screen = screenCreator.createScreen();
        assertNotNull(screen);
        assertNotNull(screenCreator.getTerminal());
    }
}
