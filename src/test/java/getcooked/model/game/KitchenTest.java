package getcooked.model.game;

import getcooked.model.Position;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.elements.interactable.Interactable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KitchenTest {
    private Kitchen kitchen;
    private List<Chef> chefs;
    private List<Tile> tiles;

    @BeforeEach
    void setUp() {
       this.tiles = new ArrayList<>(); // can be modified later in tests
       this.chefs = new ArrayList<>(); // can be modified later in tests
        Deque<Order> levelOrders = new ArrayDeque<>(Arrays.asList(
                new Order(new Dish(DishType.POTATO_SOUP), 30),
                new Order(new Dish(DishType.CARROT_SOUP), 45),
                new Order(new Dish(DishType.CARROT_SOUP), 60)
        ));

       this.kitchen = new Kitchen(10, 10, 1, 1,
                chefs, tiles, levelOrders,
                new ArrayList<>(), 3, 0);
    }

    @Test
    void testDecreaseLives() {
        assertEquals(3, kitchen.getLives());
        kitchen.decreaseLives();
        assertEquals(2, kitchen.getLives());
        kitchen.decreaseLives();
        assertEquals(1, kitchen.getLives());
    }

    @Test
    void testGetLevelOrders() {
        // convert to list to compare by elements
        List<Order> expected = Arrays.asList(
                new Order(new Dish(DishType.POTATO_SOUP), 30),
                new Order(new Dish(DishType.CARROT_SOUP), 45),
                new Order(new Dish(DishType.CARROT_SOUP), 60)
        );
        assertEquals(expected, new ArrayList<>(kitchen.getLevelOrders()));
    }

    @Test
    void testAddActiveOrder() {
        Order newOrder = new Order(new Dish(DishType.POTATO_SOUP), 30);
        kitchen.addActiveOrder(newOrder);
        assertEquals(1, kitchen.getActiveOrders().size());
        assertEquals(newOrder, kitchen.getActiveOrders().getLast());
    }

    @Test
    void testRemoveActiveOrder() {
        Order newOrder1 = new Order(new Dish(DishType.POTATO_SOUP), 30);
        Order newOrder2 = new Order(new Dish(DishType.CARROT_SOUP), 45);
        kitchen.addActiveOrder(newOrder1);
        kitchen.addActiveOrder(newOrder2);

        kitchen.removeActiveOrder(newOrder1);
        assertEquals(1, kitchen.getActiveOrders().size());
        assertEquals(newOrder2, kitchen.getActiveOrders().getFirst());
    }

    @Test
    void testGetActiveOrders() {
        Order newOrder = new Order(new Dish(DishType.POTATO_SOUP), 30);
        kitchen.addActiveOrder(newOrder);
        assertEquals(1, kitchen.getActiveOrders().size());
    }

    @Test
    void testGetTimeForNewOrder() {
        assertEquals(0, kitchen.getTimeForNewOrder());
    }

    @Test
    void testSetTimeForNewOrder() {
        kitchen.setTimeForNewOrder(5);
        assertEquals(5, kitchen.getTimeForNewOrder());
    }
    

    @Test
    void test_getTile() {
        Tile mockedTile = mock(Tile.class);
        Position tilePosition = new Position(1, 1);
        when(mockedTile.getPosition()).thenReturn(tilePosition);

        tiles.add(mockedTile);

        assertEquals(mockedTile, kitchen.getTile(new Position(1, 1)));
        assertNull(kitchen.getTile(new Position(0, 0)));
    }

    @Test
    void test_isEmpty() {
        // add a floor tile at (2,2)
        Floor mockedFloor = mock(Floor.class);
        Position floorPosition = new Position(2, 2);
        when(mockedFloor.getPosition()).thenReturn(floorPosition);

        tiles.add(mockedFloor);

        // add an interactable at (3,3)
        Interactable mockedInteractable = mock(Interactable.class);
        Position interactablePosition = new Position(3, 3);
        when(mockedInteractable.getPosition()).thenReturn(interactablePosition);

        tiles.add(mockedInteractable);

        // add a chef at (4,4)
        Chef mockedChef = mock(Chef.class);
        Position chefPosition = new Position(4, 4);
        when(mockedChef.getPosition()).thenReturn(chefPosition);

        chefs.add(mockedChef);

        assertTrue(kitchen.isEmpty(new Position(1, 1)));
        assertTrue(kitchen.isEmpty(new Position(2, 2))); // a floor means the tile is empty
        assertFalse(kitchen.isEmpty(new Position(3, 3))); // mocked interactable is there
        assertFalse(kitchen.isEmpty(new Position(4, 4))); // mocked chef is there
    }

}
