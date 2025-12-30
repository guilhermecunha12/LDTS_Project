package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Fryer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class FryerViewerTest {
    private GUI gui;
    private FryerViewer fryerViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        fryerViewer = new FryerViewer();
    }

    @Test
    void testDrawFryerEmptyRight() throws IOException, URISyntaxException, FontFormatException {
        Fryer fryer = mock(Fryer.class);

        when(fryer.isEmpty()).thenReturn(true);
        when(fryer.getType()).thenReturn('R');

        fryerViewer.draw(fryer, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawFryerExecutingRight() throws IOException, URISyntaxException, FontFormatException {
        Fryer fryer = mock(Fryer.class);

        when(fryer.isEmpty()).thenReturn(false);
        when(fryer.isExecuting()).thenReturn(true);
        when(fryer.getType()).thenReturn('R');

        fryerViewer.draw(fryer, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawFryerFinishedRIght() throws IOException, URISyntaxException, FontFormatException {
        Fryer fryer = mock(Fryer.class);

        when(fryer.isEmpty()).thenReturn(false);
        when(fryer.isFinished()).thenReturn(false);
        when(fryer.getType()).thenReturn('R');

        fryerViewer.draw(fryer, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
