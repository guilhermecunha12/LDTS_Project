package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.FoodState;
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

class CarrotViewerTest {
    private CarrotViewer carrotViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = org.mockito.Mockito.mock(GUI.class);
        carrotViewer = new CarrotViewer();
    }

    @Test
    void testDrawCarrotAllStates() throws IOException, URISyntaxException, FontFormatException {
        FoodState[] validStates= {RAW, CHOPPED};

        for(FoodState foodState : validStates) {
            Carrot carrot = mock(Carrot.class);

            when(carrot.getState()).thenReturn(foodState);

            carrotViewer.draw(carrot, gui);

            verify(gui,atLeastOnce()).drawPixel(anyInt(),anyInt(),any());
        }
    }

}
