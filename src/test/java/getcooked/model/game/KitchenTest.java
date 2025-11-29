package getcooked.model.game;

import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.elements.interactable.Interactable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
       this.kitchen = new Kitchen(10, 10, 1, 1,
                chefs, tiles, null,
                List.of(), 3, 0);
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
