package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class PotatoViewerTest{
    private GUI gui;
    private PotatoViewer potatoViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        potatoViewer = new PotatoViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        Potato potato = mock(Potato.class);

        when(potato.getState()).thenReturn(FoodState.RAW);
        potatoViewer.draw(potato, gui);

        org.mockito.Mockito.verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
