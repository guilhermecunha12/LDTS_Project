package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.elements.Floor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FloorViewerTest {
    private GUI gui;
    private Floor floor;
    private FloorViewer floorViewer;

    @BeforeEach
    void setup() {
        gui = mock(GUI.class);
        floor = mock(Floor.class);
        floorViewer = new FloorViewer();
    }

    @Test
    void testDraw() {
        Position position = mock(Position.class);

        when(floor.getPosition()).thenReturn(position);

        floorViewer.draw(floor, gui);

        verify(gui,times(1)).drawFloor(position);
    }
}
