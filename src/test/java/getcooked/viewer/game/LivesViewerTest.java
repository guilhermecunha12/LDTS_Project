package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class LivesViewerTest {
    private GUI gui;
    private Kitchen kitchen;
    private LivesViewer livesViewer;

    @BeforeEach
    void setup() {
        gui = mock(GUI.class);
        kitchen = mock(Kitchen.class);
        livesViewer = new LivesViewer(kitchen);
    }

    @Test
    void testDrawLives() {
        when(kitchen.getLives()).thenReturn(3);
        when(gui.getHeight()).thenReturn(20);

        livesViewer.drawElements(gui);

        verify(gui, times(1)).drawText(new getcooked.model.Position(1, 11), "Lives: ", "#ffffff", "#000000");
        verify(gui, times(3)).drawRectangle(any(getcooked.model.Position.class), eq(3), eq(3), eq("#ff0000"));
    }
}
