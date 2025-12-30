package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Salad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class SaladViewerTest {
    private GUI gui;
    private SaladViewer saladViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        saladViewer = new SaladViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        Salad salad = mock(Salad.class);

        saladViewer.draw(salad, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

}
