package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.Potato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PotTest {
    private Pot pot;

    @BeforeEach
    void testPotCreation(){
        pot = new Pot(5,10);
    }

    @Test
    void testAccepetsPotato(){
        Potato potato = mock(Potato.class);
        when(potato.getState()).thenReturn(Food.FoodState.RAW);

        assertTrue(pot.tryAdd(potato));
    }

    @Test
    void testAccepetsCarrotAndIsFull(){
        Carrot carrot = mock(Carrot.class);
        when(carrot.getState()).thenReturn(Food.FoodState.RAW);

        assertTrue(pot.tryAdd(carrot));

        Carrot carrot2 = mock(Carrot.class);
        when(carrot2.getState()).thenReturn(Food.FoodState.RAW);
        // content is full now
        assertFalse(pot.tryAdd(carrot2));
    }

    @Test
    void testDoesNotAcceptOtherFood(){
        Food food = mock(Food.class);

        assertFalse(pot.tryAdd(food));
    }

    @Test
    void testTryProcessFood(){
        Potato potato = mock(Potato.class);
        when(potato.getState()).thenReturn(Food.FoodState.RAW);

        pot.tryAdd(potato);

        assertTrue(pot.isCooked());
    }

    @Test
    void testDoesNotProcessFoodWhenEmpty(){
        pot.clearCookware();
        pot.tryCook();
        assertFalse(pot.isCooked());
    }

    @Test
    void testCreateFinishedDishPotatoSoup(){
        Potato potato = mock(Potato.class);
        when(potato.getState()).thenReturn(Food.FoodState.RAW);

        pot.tryAdd(potato);
        pot.tryCook();
        //cooked = true now

        assertEquals(getcooked.model.game.dish.DishType.POTATO_SOUP,pot.createFinishedDish().getType());
        assertFalse(pot.isCooked());
    }

    @Test
    void testCreateFinishedDishCarrotSoup(){
        Carrot carrot = mock(Carrot.class);
        when(carrot.getState()).thenReturn(Food.FoodState.RAW);

        pot.tryAdd(carrot);
        pot.tryCook();
        //cooked = true now

        assertEquals(getcooked.model.game.dish.DishType.CARROT_SOUP, pot.createFinishedDish().getType());
        assertFalse(pot.isCooked());
    }

    @Test
    void testCreateFinishedDishNot(){

        pot.tryCook();

        assertNull(pot.createFinishedDish());
    }
}
