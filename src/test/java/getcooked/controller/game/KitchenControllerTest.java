package getcooked.controller.game;

import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class KitchenControllerTest {
    private Kitchen kitchen;
    private KitchenController kitchenController;

    @BeforeEach
    void setUp() {
        this.kitchen = mock(Kitchen.class);
        //playing session is not needed for this test, so we pass null
        this.kitchenController = new KitchenController(kitchen, null);
    }

    @Test
    void testChefKitchenControllerInitialization() {
        Chef chef1 = mock(Chef.class);
        Chef chef2 = mock(Chef.class);
        when(kitchen.getChefs()).thenReturn(List.of(chef1, chef2));
        KitchenController controller = new KitchenController(kitchen, null);
        // Verify that only one ChefController is created
        verify(kitchen, times(2)).getChefs();
    }

    //TODO: implement step tests
}
