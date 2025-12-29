package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.FoodState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testDraw() throws Exception {
        getcooked.model.game.elements.grabable.food.Carrot carrot = org.mockito.Mockito.mock(getcooked.model.game.elements.grabable.food.Carrot.class);
        when(carrot.getState()).thenReturn(FoodState.RAW);

        carrotViewer.draw(carrot, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
