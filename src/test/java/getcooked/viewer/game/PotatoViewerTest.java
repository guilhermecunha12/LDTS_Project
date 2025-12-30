package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.model.game.elements.grabable.food.FoodState.CHOPPED;
import static getcooked.model.game.elements.grabable.food.FoodState.RAW;
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
    void testDrawPotatoAllStates() throws IOException, URISyntaxException, FontFormatException {
        FoodState[] validStates= {RAW, CHOPPED};

        for(FoodState state : validStates){
            Potato potato = mock(Potato.class);

            when(potato.getState()).thenReturn(state);

            potatoViewer.draw(potato,gui);

            verify(gui,atLeastOnce()).drawPixel(anyInt(),anyInt(),any());
        }
    }
}
