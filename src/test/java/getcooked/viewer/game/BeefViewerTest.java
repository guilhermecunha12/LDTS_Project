package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Beef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class BeefViewerTest {
    private BeefViewer beefViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        beefViewer = new BeefViewer();
    }

    @Test
    void testDrawBeef() throws IOException, URISyntaxException, FontFormatException {
        Beef beef = mock(Beef.class);

        beefViewer.draw(beef, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
