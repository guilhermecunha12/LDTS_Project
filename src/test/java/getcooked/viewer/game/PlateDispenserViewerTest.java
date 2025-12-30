package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PlateDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class PlateDispenserViewerTest {
    private GUI gui;
    private PlateDispenserViewer plateDispenserViewer;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.plateDispenserViewer = new PlateDispenserViewer();
    }

    @Test
    void testDrawPlate() throws IOException, URISyntaxException, FontFormatException {
        PlateDispenser plateDispenser = mock(PlateDispenser.class);

        plateDispenserViewer.draw(plateDispenser, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
