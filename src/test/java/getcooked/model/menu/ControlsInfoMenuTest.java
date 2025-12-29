package getcooked.model.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControlsInfoMenuTest {
    private ControlsInfoMenu controlsInfoMenu;

    @Test
    void test1PlayerTrue(){
        controlsInfoMenu = new ControlsInfoMenu(true);
        assertTrue(controlsInfoMenu.isOnePlayer());
    }

    @Test
    void test1PlayerFalse(){
        controlsInfoMenu = new ControlsInfoMenu(false);
        assertFalse(controlsInfoMenu.isOnePlayer());
    }

}
