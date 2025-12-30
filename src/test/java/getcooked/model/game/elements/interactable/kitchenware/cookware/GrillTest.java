package getcooked.model.game.elements.interactable.kitchenware.cookware;

import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.*;
import getcooked.viewer.game.GrillViewer;
import net.jqwik.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GrillTest {
    private Grill grill;

    @BeforeEach
    void setUp() {
        grill = new Grill(0, 0);
    }

    @Test
    void getViewer() {
        assertInstanceOf(GrillViewer.class, grill.getViewer());
    }


    @Provide
    Arbitrary<Food> foodsExceptBeef() {
        return Arbitraries.of(new Potato(0,0), new Salad(0,0), new Soda(0,0), new Carrot(0,0));
    }

    @Property
    void testDoesNotAcceptOtherFood(@ForAll("foodsExceptBeef") Food food) {
        setUp();
        assertFalse(grill.canAdd(food));
    }

    @Test
    void testAcceptsBeef() {
        Beef beef = new Beef(0, 0);

        assertTrue(grill.canAdd(beef));
    }

    @Test
    void testDoesNotAcceptChoppedBeef() {
        Beef beef = new Beef(0, 0);
        beef.setState(FoodState.CHOPPED); // this should never happen

        assertFalse(grill.canAdd(beef));
    }

    @Test
    void testCreateFinishedDish() {
        Beef beef = mock(Beef.class);
        Plate plate = mock(Plate.class);
        when(plate.hasContent()).thenReturn(false);

        grill.addContent(beef);
        grill.finish();
        grill.acceptPlateInChef(plate, mock(Chef.class));

        verify(plate, times(1)).setContent(argThat(dish -> dish.type() == DishType.STEAK));
    }
}