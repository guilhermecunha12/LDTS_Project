package getcooked.controller.game;

import getcooked.Game;
import getcooked.model.game.Kitchen;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public class KitchenControllerTest {
    private Kitchen kitchen;
    private Game game;
    private KitchenController kitchenController;

    @BeforeEach
    void setUp() {
        this.kitchen = mock(Kitchen.class);
        this.game = mock(Game.class);
        this.kitchenController = new KitchenController(kitchen);
    }
}