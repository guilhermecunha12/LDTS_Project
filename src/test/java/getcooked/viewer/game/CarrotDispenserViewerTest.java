package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.elements.interactable.dispenser.CarrotDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CarrotDispenserViewerTest {
    private GUI gui;
    private CarrotDispenser carrotDispenser;
    private CarrotDispenserViewer carrotDispenserViewer;


    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        carrotDispenser = mock(CarrotDispenser.class);
        carrotDispenserViewer = new CarrotDispenserViewer();
    }

    @Test
    void testDraw() {
        Position position = mock(Position.class);

        when(carrotDispenser.getPosition()).thenReturn(position);

        carrotDispenserViewer.draw(carrotDispenser, gui);

        verify(gui,times(1)).drawCarrotDispenser(position);
    }
}
