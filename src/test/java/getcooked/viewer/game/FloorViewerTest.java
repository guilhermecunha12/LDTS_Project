package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Floor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class FloorViewerTest {
    private FloorViewer floorViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        floorViewer = new FloorViewer();
    }

    @Test
    void testDrawFloor() throws IOException, URISyntaxException, FontFormatException {
        Floor floor = mock(Floor.class);

        floorViewer.draw(floor, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(),anyInt(),any());
    }
}
