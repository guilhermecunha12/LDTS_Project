package getcooked.gui;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class FontManagerTest {
    private FontManager fontManager;

    @BeforeEach
    void setUp() throws Exception {
        fontManager = new FontManager();
    }


    @Test
    void testConstructorLoadsFontResources() {
        assertNotNull(fontManager.getFontImage());
        assertNotNull(fontManager.getCharPositionCache());
        assertFalse(fontManager.getCharPositionCache().isEmpty());

    }

    @Test
    void testFontSizes() {
        int size1 = fontManager.bestFontSize(720, 1280);
        int size2 = fontManager.bestFontSize(1080, 1920);
        int size3 = fontManager.bestFontSize(1440, 2560);
        int size4 = fontManager.bestFontSize(2160, 3840);

        assertTrue(size1 > 0);
        assertTrue(size2 > size1);
        assertTrue(size3 > size2);
        assertTrue(size4 > size3);
    }

    @Test
    void testFontSizeInicialization() {
        assertTrue(fontManager.getFontSize() > 0);
    }

    @Test
    void testCharacterPositionExistsForKnownCharacter() throws IOException {
        fontManager = new FontManager();

        assertTrue(fontManager.getCharPositionCache().containsKey('A'));
    }

    @Test
    void testLoadPixelFont() throws URISyntaxException, IOException, FontFormatException {
        AWTTerminalFontConfiguration config = fontManager.loadPixelFont();

        assertNotNull(config);
    }
}
