package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;

class PotatoDispenserViewerTest {
    private GUI gui;
    private PotatoDispenserViewer potatoDispenserViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        potatoDispenserViewer = new PotatoDispenserViewer();
    }

    @Test
    void testDrawPotatoDispenser() throws IOException, URISyntaxException, FontFormatException {
        PotatoDispenser potatoDispenser = mock(PotatoDispenser.class);

        potatoDispenserViewer.draw(potatoDispenser, gui);

        org.mockito.Mockito.verify(gui, org.mockito.Mockito.atLeastOnce()).drawPixel(org.mockito.ArgumentMatchers.anyInt(), org.mockito.ArgumentMatchers.anyInt(), org.mockito.ArgumentMatchers.any());
    }
}
