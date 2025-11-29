package getcooked.model.game.elements;

import getcooked.model.game.Kitchen;
import getcooked.model.game.KitchenBuilder;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KitchenBuilderTest {
    private KitchenBuilder kitchenBuilder;

    @Test
    void testCreate1PlayerKitchen() {
        kitchenBuilder = new KitchenBuilder(1, 1, 3, new ArrayList<>(Arrays.asList(DishType.POTATO_SOUP))) {
            @Override
            protected int getWidth() {
                return 20;
            }

            @Override
            protected int getHeight() {
                return 20;
            }

            @Override
            protected List<Tile> createTiles() {
                return List.of(new Floor(0,0), new PotatoDispenser(1,0));
            }
        };

        Kitchen kitchen = kitchenBuilder.createKitchen();

        assertEquals(1, kitchen.getLevel());
        assertEquals(20, kitchen.getWidth());
        assertEquals(20, kitchen.getHeight());
        assertEquals(1, kitchen.getNumPlayers());
        assertEquals(3, kitchen.getLives());
        assertEquals(1, kitchen.getChefs().size());
        assertEquals(2, kitchen.getTiles().size());
    }

    @Test
    void testCreate2PlayersKitchen() {
        kitchenBuilder = new KitchenBuilder(2, 2, 3, new ArrayList<>(Arrays.asList(DishType.POTATO_SOUP))) {
            @Override
            protected int getWidth() {
                return 30;
            }

            @Override
            protected int getHeight() {
                return 30;
            }

            @Override
            protected List<Tile> createTiles() {
                return List.of(new Floor(0,0), new PotatoDispenser(1,0), new PotatoDispenser(2,0));
            }
        };

        Kitchen kitchen = kitchenBuilder.createKitchen();

        assertEquals(2, kitchen.getLevel());
        assertEquals(30, kitchen.getWidth());
        assertEquals(30, kitchen.getHeight());
        assertEquals(2, kitchen.getNumPlayers());
        assertEquals(3, kitchen.getLives());
        assertEquals(2, kitchen.getChefs().size());
        assertEquals(3, kitchen.getTiles().size());
    }


}
