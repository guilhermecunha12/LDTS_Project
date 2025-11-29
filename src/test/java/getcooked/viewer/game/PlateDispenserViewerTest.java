package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.elements.interactable.dispenser.PlateDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PlateDispenserViewerTest {
    private GUI gui;
    private PlateDispenser plateDispenser;
    private PlateDispenserViewer plateDispenserViewer;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.plateDispenser = mock(PlateDispenser.class);
        this.plateDispenserViewer = new PlateDispenserViewer();
    }

    @Test
    void testDrawDeliverySpot(){
        Position position = mock(getcooked.model.Position.class);

        when(plateDispenser.getPosition()).thenReturn(position);

        plateDispenserViewer.draw(plateDispenser, gui);

        verify(gui,times(1)).drawPlateDispenser(position);
    }
}
