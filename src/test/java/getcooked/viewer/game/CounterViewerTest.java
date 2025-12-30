package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.Grabable;
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
    void testDrawCounterEmptyAllTypes() throws IOException, URISyntaxException, FontFormatException {
        char[] availableTypes = {'u','d','l','r','1','2','3','4'};

        for(char type :  availableTypes){
            Counter counter = mock(Counter.class);

            when(counter.getType()).thenReturn(type);
            when(counter.getContent()).thenReturn(null);

            counterViewer.draw(counter,gui);

            verify(gui,atLeastOnce()).drawPixel(anyInt(),anyInt(),any());
        }
    }

    @Test
    void testDrawCounterWithContentAllTypes() throws IOException, URISyntaxException, FontFormatException {
        char[] availableTypes = {'u','d','l','r','1','2','3','4'};

        for(char type :  availableTypes){
            Counter counter = mock(Counter.class);
            Grabable grabable = mock(Grabable.class);
            ElementViewer<Grabable> grabableViewer = mock(ElementViewer.class);

            when(counter.getType()).thenReturn(type);
            when(counter.getContent()).thenReturn(grabable);
            when(grabable.getViewer()).thenReturn(grabableViewer);

            counterViewer.draw(counter,gui);

            verify(grabableViewer,times(1)).draw(grabable,gui);
            verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
        }
    }
}
