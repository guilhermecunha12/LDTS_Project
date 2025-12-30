package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class ChefViewerTest {
    private ChefViewer chefViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        chefViewer = new ChefViewer(1);
    }

    @Test
    void testDrawChefNotHoldingSomethingAllDirections() throws IOException, URISyntaxException, FontFormatException {
        for(Chef.Direction direction : Chef.Direction.values()) {
            Chef chef = mock(Chef.class);

            when(chef.getDirection()).thenReturn(direction);
            when(chef.isHoldingSomething()).thenReturn(false);

            chefViewer.draw(chef,gui);

            verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
        }
    }

    @Test
    void testDrawChefHoldingSomethingAllDirections() throws IOException, URISyntaxException, FontFormatException {
        for(Chef.Direction direction : Chef.Direction.values()) {
            Chef chef = mock(Chef.class);
            Grabable grabable = mock(Grabable.class);
            ElementViewer<Grabable> grabableViewer = mock(ElementViewer.class);

            when(chef.getDirection()).thenReturn(direction);
            when(chef.isHoldingSomething()).thenReturn(true);
            when(chef.getItem()).thenReturn(grabable);
            when(grabable.getViewer()).thenReturn(grabableViewer);

            chefViewer.draw(chef,gui);

            verify(grabableViewer,times(1)).draw(grabable,gui);
            verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
        }
    }
}
