package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.Counter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class CounterViewerTest {
    private GUI gui;
    private CounterViewer counterViewer;

    @BeforeEach
    void setUp(){
        gui = mock(GUI.class);
        counterViewer = new CounterViewer();
    }

    @Test
    void testDrawEmptyCounter() throws IOException, URISyntaxException, FontFormatException {
        Counter counter = mock(Counter.class);

        when(counter.getType()).thenReturn('u');
        when(counter.getX()).thenReturn(0);
        when(counter.getY()).thenReturn(0);
        when(counter.getContent()).thenReturn(null);

        counterViewer.draw(counter,gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawCounterWithItem() throws IOException, URISyntaxException, FontFormatException {
        Counter counter = mock(Counter.class);
        var grabable = mock(getcooked.model.game.elements.grabable.Grabable.class);
        var grabableViewer = mock(getcooked.viewer.game.ElementViewer.class);

        when(counter.getType()).thenReturn('d');
        when(counter.getX()).thenReturn(0);
        when(counter.getY()).thenReturn(0);
        when(counter.getContent()).thenReturn(grabable);
        when(grabable.getViewer()).thenReturn(grabableViewer);

        counterViewer.draw(counter, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
        verify(grabableViewer, times(1)).draw(grabable, gui);
    }
}
