package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.CarrotDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class CarrotDispenserTest {
    private GUI gui;
    private CarrotDispenserViewer carrotDispenserViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        carrotDispenserViewer = new CarrotDispenserViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        CarrotDispenser carrotDispenser = mock(CarrotDispenser.class);

        carrotDispenserViewer.draw(carrotDispenser, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
