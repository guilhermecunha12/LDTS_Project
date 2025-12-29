package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Grill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class GrillViewerTest {
    private GUI gui;
    private GrillViewer grilViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        grilViewer = new GrillViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        Grill grill = mock(Grill.class);

        when(grill.getX()).thenReturn(0);
        when(grill.getY()).thenReturn(0);
        when(grill.isEmpty()).thenReturn(true);

        grilViewer.draw(grill, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
