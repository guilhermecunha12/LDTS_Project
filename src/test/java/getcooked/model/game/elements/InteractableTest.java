package getcooked.model.game.elements;

import getcooked.viewer.game.ElementViewer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InteractableTest {

    @Test
    void testIsBlocking() {
        Interactable interactable = new Interactable(0, 0) {
            @Override
            public ElementViewer getViewer() {
                return null;
            }

            @Override
            public void interact(Chef chef) {
                // Default implementation ignored
            }
        };

        assertTrue(interactable.isBlocking());
    }
}