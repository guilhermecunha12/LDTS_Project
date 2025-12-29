package getcooked.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOverMenuTest {
    private GameOverMenu menu;

    @BeforeEach
    void setUp() {
        menu = new GameOverMenu(5);
    }

    @Test
    void testIsSelectedBackToMainMenu() {
        menu.currentEntry = 0;
        assertTrue(menu.isSelectedBackToMainMenu());
        assertFalse(menu.isSelectedExitGame());
    }

    @Test
    void testIsSelectedExitGame() {
        menu.currentEntry = 1;
        assertFalse(menu.isSelectedBackToMainMenu());
        assertTrue(menu.isSelectedExitGame());
    }

    @Test
    void testGetOrdersDelivered() {
        assertEquals(5, menu.getOrdersDelivered());
    }
}
