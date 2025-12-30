package getcooked.model.game.elements.interactable.kitchenware.cookware;

import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.*;
import getcooked.viewer.game.FryerViewer;
import net.jqwik.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FryerTest {
    private Fryer fryer;

    @BeforeEach
    void setUp() {
        fryer = new Fryer(0, 0, 'F');
    }

    @Test
    void getViewer() {
        assertInstanceOf(FryerViewer.class, fryer.getViewer());
    }

    @Provide
    Arbitrary<Food> foodsExceptPotato() {
        return Arbitraries.of(new Carrot(0,0), new Beef(0,0), new Salad(0,0), new Soda(0,0));
    }

    @Property
    void testDoesNotAcceptOtherFood(@ForAll("foodsExceptPotato") Food food) {
        setUp();
        assertFalse(fryer.canAdd(food));
    }

    @Test
    void testAcceptsPotato() {
        Potato potato = new Potato(0,0);
        potato.setState(FoodState.CHOPPED);

        assertTrue(fryer.canAdd(potato));
    }

    @Test
    void testDoesNotAcceptRawPotato() {
        Potato potato = new Potato(0,0);
        potato.setState(FoodState.RAW);

        assertFalse(fryer.canAdd(potato));
    }

    @Test
    void testCreateFinishedDish() {
        Potato potato = mock(Potato.class);
        Plate plate = mock(Plate.class);
        when(plate.hasContent()).thenReturn(false);

        fryer.addContent(potato);
        fryer.finish();
        fryer.acceptPlateInChef(plate, mock(Chef.class));

        verify(plate, times(1)).setContent(argThat(dish -> dish.type() == DishType.FRIES));
    }

    @Test
    void getType() {
        assertEquals('F', fryer.getType());
    }
}