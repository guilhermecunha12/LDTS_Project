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
    private GrillViewer grillViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        grillViewer = new GrillViewer();
    }

    @Test
    void testDrawGrillEmpty() throws IOException, URISyntaxException, FontFormatException {
        Grill grill = mock(Grill.class);

        when(grill.isEmpty()).thenReturn(true);

        grillViewer.draw(grill, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawGrillExecuting() throws IOException, URISyntaxException, FontFormatException {
        Grill grill = mock(Grill.class);

        when(grill.isEmpty()).thenReturn(false);
        when(grill.isExecuting()).thenReturn(true);

        grillViewer.draw(grill, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawGrillFinished() throws IOException, URISyntaxException, FontFormatException {
        Grill grill = mock(Grill.class);

        when(grill.isEmpty()).thenReturn(false);
        when(grill.isExecuting()).thenReturn(false);

        grillViewer.draw(grill, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
