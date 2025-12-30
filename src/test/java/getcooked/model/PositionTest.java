package getcooked.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {
    private Position position;

    @BeforeEach
    void setUp(){
        position = new Position(2,3);
    }


    @Test
    void testGetX(){
        assertEquals(2,position.x());
    }

    @Test
    void testGetY(){
        assertEquals(3,position.y());
    }

    @Test
    void testGetLeft(){
        assertEquals(new Position(1,3),position.getLeft());
    }

    @Test
    void testGetRight(){
        assertEquals(new Position(3,3),position.getRight());
    }

    @Test
    void testGetUp(){
        assertEquals(new Position(2,2),position.getUp());
    }

    @Test
    void testGetDown(){
        assertEquals(new Position(2,4),position.getDown());
    }

    @Test
    void testEqualsSameObject(){
        assertTrue(position.equals(position));
    }

    @Test
    void testEqualsNull(){
        assertFalse(position.equals(null));
    }

    @Test
    void testEqualsDifferentClass(){
        assertFalse(position.equals(new Object()));
    }

    @Test
    void testEqualsSameContent(){
        Position position1 = new Position(2,3);
        assertTrue(position.equals(position1));
    }

    @Test
    void testEqualsDifferentContent(){
        Position position1 = new Position(3,2);
        assertFalse(position.equals(position1));
    }

    @Test
    void testDifferentY(){
        Position position1 = new Position(2,4);
        assertFalse(position.equals(position1));
    }


    @Test
    void testHashCode(){
        Position position1 = new Position(2,3);
        assertEquals(position.hashCode(), position1.hashCode());
    }
}
