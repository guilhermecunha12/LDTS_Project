package getcooked.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    private Position position;

    @BeforeEach
    void setUp(){
        position = new Position(2,3);
    }

    @Test
    void getLeft(){
        assertEquals(new Position(1,3),position.getLeft());
    }

    @Test
    void getRight(){
        assertEquals(new Position(3,3),position.getRight());
    }

    @Test
    void getUp(){
        assertEquals(new Position(2,2),position.getUp());
    }

    @Test
    void getDown(){
        assertEquals(new Position(2,4),position.getDown());
    }
}
