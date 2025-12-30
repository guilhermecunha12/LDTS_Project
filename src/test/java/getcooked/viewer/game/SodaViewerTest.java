package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Soda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class SodaViewerTest {
    private GUI gui;
    private SodaViewer sodaViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        sodaViewer = new SodaViewer();
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        Soda soda = mock(Soda.class);

        sodaViewer.draw(soda, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

}
