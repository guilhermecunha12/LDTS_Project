package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.DeliverySpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class DeliverySpotViewerTest {
    private GUI gui;
    private DeliverySpotViewer deliverySpotViewer;

    @BeforeEach
    void setUp(){
        gui = mock(GUI.class);
        deliverySpotViewer = new DeliverySpotViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        DeliverySpot deliverySpot = mock(DeliverySpot.class);

        when(deliverySpot.getX()).thenReturn(0);
        when(deliverySpot.getY()).thenReturn(0);

        deliverySpotViewer.draw(deliverySpot, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
