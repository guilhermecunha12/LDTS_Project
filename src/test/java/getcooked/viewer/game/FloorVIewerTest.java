package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Floor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class FloorVIewerTest {
    private FloorViewer floorViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        floorViewer = new FloorViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        Floor floor = mock(Floor.class);

        when(floor.getX()).thenReturn(0);
        when(floor.getY()).thenReturn(0);

        floorViewer.draw(floor, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(),anyInt(),any());
    }
}
