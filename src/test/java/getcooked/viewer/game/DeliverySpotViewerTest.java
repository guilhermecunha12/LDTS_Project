package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.elements.interactable.DeliverySpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class DeliverySpotViewerTest {
    private GUI gui;
    private DeliverySpot deliverySpot;
    private DeliverySpotViewer deliverySpotViewer;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.deliverySpot = mock(DeliverySpot.class);
        this.deliverySpotViewer = new DeliverySpotViewer();
    }

    @Test
    void testDrawDeliverySpot(){
        Position position = mock(getcooked.model.Position.class);

        when(deliverySpot.getPosition()).thenReturn(position);

        deliverySpotViewer.draw(deliverySpot, gui);

        verify(gui,times(1)).drawDelivery(position);
    }
}
