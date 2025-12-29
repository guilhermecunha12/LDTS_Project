package getcooked.model.game.elements;

import getcooked.viewer.game.ElementViewer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InteractableTest {
    private Interactable interactable;

    @Test
    void testIsBlocking() {

        this.interactable = new Interactable(0, 0) {
            @Override
            public ElementViewer getViewer() {
                return null;
            }

            @Override
            public void interact(Chef chef) {
                // Default implementation ignored
            }
        };

        assertTrue(this.interactable.isBlocking());
    }
}