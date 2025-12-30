package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.SodaDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class SodaDispenserViewerTest {
    private GUI gui;
    private SodaDispenserViewer sodaDispenserViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        sodaDispenserViewer = new SodaDispenserViewer();
    }

    @Test
    void testDrawSaladDispenser() throws IOException, URISyntaxException, FontFormatException {
        SodaDispenser sodaDispenser = mock(SodaDispenser.class);

        sodaDispenserViewer.draw(sodaDispenser, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
