package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.BeefDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class BeefDispenserViewerTest {
    private BeefDispenserViewer beefDispenserViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        beefDispenserViewer = new BeefDispenserViewer();
    }

    @Test
    void testDrawDoesNotThrow() throws IOException, URISyntaxException, FontFormatException {
        BeefDispenser beefDispenser = mock(BeefDispenser.class);

        beefDispenserViewer.draw(beefDispenser, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }


}
