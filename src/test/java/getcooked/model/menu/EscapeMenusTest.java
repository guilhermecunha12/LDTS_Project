package getcooked.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EscapeMenusTest {
    private EscapeMenu menu;

    @BeforeEach
    void setUp() {
        menu = new EscapeMenu();
    }

    @Test
    void testIsSelectedResume() {
        menu.currentEntry = 0;
        assertTrue(menu.isSelectedResume());
        assertFalse(menu.isSelectedExitToMainMenu());
    }

    @Test
    void testIsSelectedExitToMainMenu() {
        menu.currentEntry = 1;
        assertFalse(menu.isSelectedResume());
        assertTrue(menu.isSelectedExitToMainMenu());
    }
}
