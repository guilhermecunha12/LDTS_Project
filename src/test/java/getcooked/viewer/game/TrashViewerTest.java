package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.elements.interactable.Trash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TrashViewerTest {
    private GUI gui;
    private Trash trash;
    private TrashViewer trashViewer;

    @BeforeEach
    public void setup() {
        gui = mock(GUI.class);
        trash = mock(Trash.class);
        trashViewer = new TrashViewer();
    }

    @Test
    public void testDraw() {
        Position position = mock(Position.class);

        when(trash.getPosition()).thenReturn(position);

        trashViewer.draw(trash, gui);

        verify(gui,times(1)).drawTrash(position);
    }
}
