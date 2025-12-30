package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.Trash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TrashViewerTest{
    private GUI gui;
    private TrashViewer trashViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        trashViewer = new TrashViewer();
    }

    @Test
    void testDrawTrash() throws IOException, URISyntaxException, FontFormatException {
        Trash trash = mock(Trash.class);

        trashViewer.draw(trash, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
