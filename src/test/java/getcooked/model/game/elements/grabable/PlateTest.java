package getcooked.model.game.elements.grabable;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;
import getcooked.viewer.game.PlateViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlateTest {

    private Plate plate;
    private Kitchenware kitchenware;
    private Chef chef;

    @BeforeEach
    void setUp() {
        this.plate = new Plate(0, 0);
        this.kitchenware = mock(Kitchenware.class);
        this.chef = mock(Chef.class);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(PlateViewer.class, plate.getViewer());
    }

    @Test
    void testHasContentNull() {
        assertFalse(plate.hasContent());
    }

    @Test
    void testHasContentNotNull() {
        plate.setContent(new Dish(DishType.SALAD));
        assertTrue(plate.hasContent());
    }

    @Test
    void testGetContent() {
        Dish dish = new Dish(DishType.SALAD);
        plate.setContent(dish);
        assertEquals(dish, plate.getContent());
    }

    @Test
    void testSetContent() {
        plate.setContent(new Dish(DishType.POTATO_SOUP));
        assertTrue(plate.hasContent());
    }

    @Test
    void testInteractWithKitchenware() {
        plate.interactWithKitchenware(kitchenware, chef);
        verify(kitchenware, times(1)).acceptPlateInChef(plate, chef);
    }

    @Test
    void testToDeliverWithContent() {
        Dish dish = new Dish(DishType.SALAD);
        plate.setContent(dish);
        assertEquals(dish, plate.toDeliver());
    }

    @Test
    void testToDeliverWithoutContent() {
        assertNull(plate.toDeliver());
    }
}