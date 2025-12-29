package getcooked.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMenuTest {
    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        mainMenu = new MainMenu();
    }

    @Test
    void testIsSelectedCook() {
        mainMenu.currentEntry = 0;
        assertTrue(mainMenu.isSelectedCook());
        assertFalse(mainMenu.isSelectedSettings());
        assertFalse(mainMenu.isSelectedExit());
    }

    @Test
    void testIsSelectedSettings() {
        mainMenu.currentEntry = 1;
        assertFalse(mainMenu.isSelectedCook());
        assertTrue(mainMenu.isSelectedSettings());
        assertFalse(mainMenu.isSelectedExit());
    }

    @Test
    void testIsSelectedExit() {
        mainMenu.currentEntry = 2;
        assertFalse(mainMenu.isSelectedCook());
        assertFalse(mainMenu.isSelectedSettings());
        assertTrue(mainMenu.isSelectedExit());
    }
}
