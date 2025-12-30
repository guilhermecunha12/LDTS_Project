package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;
import getcooked.viewer.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodTest {

    private Food food;
    private Kitchenware kitchenware;
    private Chef chef;

    @BeforeEach
    void setUp() {
        this.food = new Food(0, 0) {
            @Override
            public DishType getCookedDishType() {
                return null;
            }

            @Override
            public ElementViewer getViewer() {
                return null;
            }
        };
        this.kitchenware = mock(Kitchenware.class);
        this.chef = mock(Chef.class);
    }

    @Test
    void testGetState() {
        assertEquals(FoodState.RAW, food.getState());
    }

    @Test
    void testSetState() {
        food.setState(FoodState.CHOPPED);
        assertEquals(FoodState.CHOPPED, food.getState());
    }

    @Test
    void testInteractWithKitchenware() {
        food.interactWithKitchenware(kitchenware, chef);
        verify(kitchenware, times(1)).acceptFoodInChef(food, chef);
    }

    @Test
    void testToDeliverSoda(){
        Food sodaFood = new Food(0, 0) {
            @Override
            public DishType getCookedDishType() {
                return DishType.SODA;
            }

            @Override
            public ElementViewer getViewer() {
                return null;
            }
        };
        assertNotNull(sodaFood.toDeliver());
    }

    @Test
    void testToDeliverSalad(){
        Food saladFood = new Food(0, 0) {
            @Override
            public DishType getCookedDishType() {
                return DishType.SALAD;
            }

            @Override
            public ElementViewer getViewer() {
                return null;
            }
        };
        assertNotNull(saladFood.toDeliver());
    }

    @Test
    void testToDeliverOtherDish(){
        Food otherFood = new Food(0, 0) {
            @Override
            public DishType getCookedDishType() {
                return DishType.KEBAB;
            }

            @Override
            public ElementViewer getViewer() {
                return null;
            }
        };
        assertNull(otherFood.toDeliver());
    }
}