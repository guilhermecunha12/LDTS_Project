package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.menu.CookMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.mockito.Mockito.*;

class CookMenuControllerTest {
    private Game game;
    private CookMenu cookedMenu;
    private CookMenuController cookMenuController;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.cookedMenu = mock(CookMenu.class);
        this.cookMenuController = new CookMenuController(cookedMenu);
    }

    @Test
    void testUp() throws IOException {
        when(cookedMenu.isSelectedBack()).thenReturn(true);

        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_UP),0);

        verify(cookedMenu,times(1)).previousEntry();
    }

    @Test
    void testDown1() throws IOException {
        when(cookedMenu.isSelected1Player()).thenReturn(true);
        when(cookedMenu.isSelected2Player()).thenReturn(false);

        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_DOWN),0);

        verify(cookedMenu,times(1)).previousEntry();
    }

    @Test
    void testDown2() throws IOException {
        when(cookedMenu.isSelected1Player()).thenReturn(false);
        when(cookedMenu.isSelected2Player()).thenReturn(true);

        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_DOWN),0);

        verify(cookedMenu,times(1)).nextEntry();
    }

    @Test
    void testLeft() throws IOException {
        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_LEFT),0);

        verify(cookedMenu,times(1)).previousEntry();
    }

    @Test
    void testRight() throws IOException {
        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_RIGHT),0);

        verify(cookedMenu,times(1)).nextEntry();
    }

    @Test
    void testSelect1Player() throws IOException {
        when(cookedMenu.isSelected1Player()).thenReturn(true);
        when(cookedMenu.isSelected2Player()).thenReturn(false);
        when(cookedMenu.isSelectedBack()).thenReturn(false);

        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT),0);

        verify(game,times(1)).changeState(any(getcooked.states.menu.ControlsInfoState.class));
    }

    @Test
    void testSelect2Player() throws IOException {
        when(cookedMenu.isSelected1Player()).thenReturn(false);
        when(cookedMenu.isSelected2Player()).thenReturn(true);
        when(cookedMenu.isSelectedBack()).thenReturn(false);

        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT),0);

        verify(game,times(1)).changeState(any(getcooked.states.menu.ControlsInfoState.class));
    }

    @Test
    void testSelectBack() throws IOException {
        when(cookedMenu.isSelected1Player()).thenReturn(false);
        when(cookedMenu.isSelected2Player()).thenReturn(false);
        when(cookedMenu.isSelectedBack()).thenReturn(true);

        cookMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT),0);

        verify(game,times(1)).changeState(any(getcooked.states.menu.MainMenuState.class));
    }

}
