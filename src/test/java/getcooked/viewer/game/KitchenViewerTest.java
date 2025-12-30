package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Mockito.*;

class KitchenViewerTest {
    private GUI gui;
    private KitchenViewer kitchenViewer;
    private Kitchen kitchen;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        kitchen = mock(Kitchen.class);
        kitchenViewer = new KitchenViewer(kitchen);
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        Interactable interactable = mock(Interactable.class);
        List<Interactable> interactables = List.of(interactable,interactable,interactable,interactable);
        var interactableViewer = mock(ElementViewer.class);
        Chef chef = mock(Chef.class);
        var chefViewer = mock(ChefViewer.class);

        when(kitchen.getLevel()).thenReturn(1);
        when(gui.getWidth()).thenReturn(100);
        when(interactable.getViewer()).thenReturn(interactableViewer);
        when(kitchen.getInteractables()).thenReturn(interactables);
        when(kitchen.getChefs()).thenReturn(List.of(chef));
        when(chef.getViewer()).thenReturn(chefViewer);

        kitchenViewer.draw(gui);

        verify(gui,times(1)).drawText(new Position(15,2),"Level: 1","#ffffff");
        verify(interactableViewer,times(4)).draw(interactable, gui);
        verify(chefViewer,times(1)).draw(chef, gui);

    }
}
