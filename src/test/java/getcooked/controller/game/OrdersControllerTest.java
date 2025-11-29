package getcooked.controller.game;

import getcooked.Game;
import getcooked.model.game.Kitchen;
import org.junit.jupiter.api.BeforeEach;


import static org.mockito.Mockito.*;
//TODO: IMPLEMENT TESTS
public class OrdersControllerTest {
    private Game game;
    private Kitchen kitchen;
    private OrdersController ordersController;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.kitchen = mock(Kitchen.class);
        this.ordersController = new OrdersController(kitchen);
    }

}
