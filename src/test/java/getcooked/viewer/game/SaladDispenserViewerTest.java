package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.SaladDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class SaladDispenserViewerTest {
    private GUI gui;
    private SaladDispenserViewer saladDispenserViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        saladDispenserViewer = new SaladDispenserViewer();
    }

    @Test
    void testDrawSaladDispenser() throws IOException, URISyntaxException, FontFormatException {
        SaladDispenser saladDispenser = mock(SaladDispenser.class);

        saladDispenserViewer.draw(saladDispenser, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
