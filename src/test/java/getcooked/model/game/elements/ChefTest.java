package getcooked.model.game.elements;

import getcooked.model.Position;
import getcooked.model.game.elements.grabable.Grabable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChefTest {
    private Grabable grabable;
    private Chef chef;

    @BeforeEach
    void setUp() {
        chef = new Chef(10, 10, 0);
        grabable = mock(Grabable.class);
    }

    @Test
    void testGetIndex() {
        assertEquals(0, chef.getIndex());
    }

    @Test
    void testGetItem() {
        assertNull(chef.getItem());
        chef.pickUp(grabable);
        assertEquals(grabable, chef.getItem());
    }

    @Test
    void testPickUp() {
        assertNull(chef.getItem());
        chef.pickUp(grabable);
        assertEquals(grabable, chef.getItem());
    }

    @Test
    void testPickUpAssertion() {
        chef.pickUp(grabable);
        Grabable other = mock(Grabable.class);
        AssertionError thrown = assertThrows(AssertionError.class, () -> chef.pickUp(other));
        assertNotNull(thrown);
    }

    @Test
    void testDrop() {
        chef.pickUp(grabable);
        Grabable dropped = chef.drop();
        assertEquals(grabable, dropped);
        assertNull(chef.getItem());
    }

    @Test
    void testDropAssertion() {
        AssertionError thrown = assertThrows(AssertionError.class, () -> chef.drop());
        assertNotNull(thrown);
    }

    @Test
    void testTurnLeft() {
        chef.turnLeft();
        assertEquals(Chef.Direction.LEFT, chef.getDirection());
    }

    @Test
    void testTurnRight() {
        chef.turnRight();
        assertEquals(Chef.Direction.RIGHT, chef.getDirection());
    }

    @Test
    void testTurnUp() {
        chef.turnUp();
        assertEquals(Chef.Direction.UP, chef.getDirection());
    }

    @Test
    void testTurnDown() {
        chef.turnDown();
        assertEquals(Chef.Direction.DOWN, chef.getDirection());
    }

    @Test
    void testSetPosition() {
        // with item in hand
        chef.pickUp(grabable);
        chef.setPosition(new Position(5, 5));
        assertEquals(new Position(5, 5), chef.getPosition());
        verify(grabable).setPosition(new Position(5, 5));

        // without item in hand
        chef.drop();
        chef.setPosition(new Position(7, 7));
        assertEquals(new Position(7, 7), chef.getPosition());
    }

    @Test
    void testGetViewer() {
        assertNotNull(chef.getViewer());
    }

    @Test
    void testGetFrontPosition() {
        chef.setPosition(new Position(10, 10));

        chef.turnUp();
        assertEquals(new Position(10, 9), chef.getFrontPosition());

        chef.turnDown();
        assertEquals(new Position(10, 11), chef.getFrontPosition());

        chef.turnLeft();
        assertEquals(new Position(9, 10), chef.getFrontPosition());

        chef.turnRight();
        assertEquals(new Position(11, 10), chef.getFrontPosition());
    }

    @Test
    void testIsHoldingSomething() {
        assertFalse(chef.isHoldingSomething());
        chef.pickUp(grabable);
        assertTrue(chef.isHoldingSomething());
    }
}
