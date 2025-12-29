package getcooked.model.game.elements;

import getcooked.model.Position;
import getcooked.viewer.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementTest {
    private Element element;

    @BeforeEach
    void setUp() {
        this.element = new Element(5, 10) {
            @Override
            public ElementViewer getViewer() {
                return null;
            }
        };
    }

    @Test
    void getPosition() {
        Position position = element.getPosition();
        assertEquals(5, position.x());
        assertEquals(10, position.y());
    }

    @Test
    void testGetX() {
        assertEquals(5, element.getX());
    }

    @Test
    void testGetY() {
        assertEquals(10, element.getY());
    }

    @Test
    void setPosition() {
        element.setPosition(new Position(15, 20));
        assertEquals(15, element.getX());
        assertEquals(20, element.getY());
    }
}