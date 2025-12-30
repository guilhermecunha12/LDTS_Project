package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.Plate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PlateViewerTest {
    private GUI gui;
    private PlateViewer plateViewer;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.plateViewer = new PlateViewer();
    }

    @Test
    void testDrawPlateEmpty() throws IOException, URISyntaxException, FontFormatException {
        Plate plate = mock(Plate.class);

        when(plate.getContent()).thenReturn(null);

        plateViewer.draw(plate, gui);

        verify(gui,atLeastOnce()).drawPixel(anyInt(),anyInt(),any());
    }

    @Test
    void testDrawPlateWithAllDishTypes() throws IOException, URISyntaxException, FontFormatException {
        DishType[] supportedDishTypes = {
                DishType.POTATO_SOUP,
                DishType.CARROT_SOUP,
                DishType.STEAK,
                DishType.FRIES,
                DishType.KEBAB
        };

        for(DishType dishType : supportedDishTypes) {
            Plate plate = mock(Plate.class);
            Dish dish = new Dish(dishType);

            when(plate.getContent()).thenReturn(dish);

            plateViewer.draw(plate, gui);
        }

        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawPlateDefault() {
        Plate plate = mock(Plate.class);
        Dish dish = new Dish(DishType.SALAD);

        when(plate.getContent()).thenReturn(dish);

        Exception exception = assertThrows(IOException.class, () -> {plateViewer.draw(plate, gui);});

        assertEquals("Invalid plate state", exception.getMessage());
    }

}
