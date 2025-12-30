package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CounterCutterViewerTest {
    private CounterCutterViewer counterCutterViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        counterCutterViewer = new CounterCutterViewer();
    }

    @Test
    void testDrawCounterCutterEmpty() throws Exception {
        CounterCutter counterCutter = mock(CounterCutter.class);

        when(counterCutter.isEmpty()).thenReturn(true);

        counterCutterViewer.draw(counterCutter, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawCounterCutterWithContent() throws Exception {
        CounterCutter counterCutter = mock(CounterCutter.class);
        Food content = mock(Food.class);
        ElementViewer<Grabable> contentViewer = mock(ElementViewer.class);

        when(counterCutter.isEmpty()).thenReturn(false);
        when(counterCutter.getContent()).thenReturn(content);
        when(content.getViewer()).thenReturn(contentViewer);

        counterCutterViewer.draw(counterCutter, gui);

        verify(contentViewer).draw(content, gui);
        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
