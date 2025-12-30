package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class LivesViewerTest {
    private LivesViewer livesViewer;
    private GUI gui;
    private Kitchen kitchen;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        kitchen = mock(Kitchen.class);
        livesViewer = new LivesViewer(kitchen);
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        when(kitchen.getLives()).thenReturn(1);

        livesViewer.draw(gui);

        verify(gui,times(1)).drawText(new Position(270,20),"Lives: ", "#ffffff");
        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

}
