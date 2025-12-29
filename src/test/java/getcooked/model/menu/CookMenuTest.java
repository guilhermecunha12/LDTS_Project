package getcooked.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CookMenuTest{
    private CookMenu menu;

    @BeforeEach
    void setUp() {
        menu = new CookMenu();
    }

    @Test
    void testIsSelected1Player() {
        menu.currentEntry = 0;
        assertTrue(menu.isSelected1Player());
        assertFalse(menu.isSelected2Player());
        assertFalse(menu.isSelectedBack());
    }

    @Test
    void testIsSelect2Player(){
        menu.currentEntry = 1;
        assertFalse(menu.isSelected1Player());
        assertTrue(menu.isSelected2Player());
        assertFalse(menu.isSelectedBack());
    }

    @Test
    void testIsSelectedBack(){
        menu.currentEntry = 2;
        assertFalse(menu.isSelected1Player());
        assertFalse(menu.isSelected2Player());
        assertTrue(menu.isSelectedBack());
    }
}
