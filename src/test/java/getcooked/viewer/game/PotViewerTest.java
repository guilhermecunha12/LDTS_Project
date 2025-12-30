package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class PotViewerTest {
    private GUI gui;
    private PotViewer potViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        potViewer = new PotViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        Pot pot = mock(Pot.class);

        when(pot.isEmpty()).thenReturn(true);

        potViewer.draw(pot, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
