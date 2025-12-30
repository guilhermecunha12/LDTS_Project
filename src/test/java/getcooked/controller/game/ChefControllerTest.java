package getcooked.controller.game;

import getcooked.Game;
import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.interactable.Interactable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.mockito.Mockito.*;

class ChefControllerTest {
    private Chef chef;
    private Kitchen kitchen;
    private ChefController chefController;
    private Game game;

    @BeforeEach
    void setUp() {
        this.chef = mock(Chef.class);
        this.kitchen = mock(Kitchen.class);
        this.game = mock(Game.class);
        this.chefController = new ChefController(chef, kitchen);
    }

    @Test
    void testChef1StepLeft() throws IOException {
        Position currentPos = new Position(5,5);
        Position leftPos = new Position(4,5);

        when(chef.getIndex()).thenReturn(0);
        when(chef.getPosition()).thenReturn(currentPos);
        when(kitchen.isEmpty(leftPos)).thenReturn(true);

        chefController.step(game, Set.of(GUI.ACTION.PLAYER1_LEFT),0);

        verify(chef,times(1)).setPosition(leftPos);
        verify(chef,times(1)).turnLeft();
    }

    @Test
    void testChef2StepUp() throws IOException {
        Position currentPos = new Position(5,5);
        Position upPos = new Position(5,4);

        when(chef.getIndex()).thenReturn(1);
        when(chef.getPosition()).thenReturn(currentPos);
        when(kitchen.isEmpty(upPos)).thenReturn(true);

        chefController.step(game, Set.of(GUI.ACTION.PLAYER2_UP),0);

        verify(chef,times(1)).setPosition(upPos);
        verify(chef,times(1)).turnUp();
    }

    @Test
    void testChef1StepDown() throws IOException {
        Position currentPos = new Position(5,5);
        Position downPos = new Position(5,6);

        when(chef.getIndex()).thenReturn(0);
        when(chef.getPosition()).thenReturn(currentPos);
        when(kitchen.isEmpty(downPos)).thenReturn(true);

        chefController.step(game, Set.of(GUI.ACTION.PLAYER1_DOWN),0);

        verify(chef,times(1)).setPosition(downPos);
        verify(chef,times(1)).turnDown();
    }

    @Test
    void testChef2StepRight() throws IOException {
        Position currentPos = new Position(5,5);
        Position rightPos = new Position(6,5);

        when(chef.getIndex()).thenReturn(1);
        when(chef.getPosition()).thenReturn(currentPos);
        when(kitchen.isEmpty(rightPos)).thenReturn(true);

        chefController.step(game, Set.of(GUI.ACTION.PLAYER2_RIGHT),0);

        verify(chef,times(1)).setPosition(rightPos);
        verify(chef,times(1)).turnRight();
    }

    @Test
    void testMoveNotEmpty(){
        Position currentPos = new Position(5,5);
        when(kitchen.isEmpty(currentPos)).thenReturn(false);

        chefController.moveChef(currentPos);

        verify(chef,never()).setPosition(currentPos);
    }

    @Test
    void testChef1Select() throws IOException {
        Position frontPos = new Position(5,4);
        Interactable frontInteractable = mock(Interactable.class);

        when(chef.getFrontPosition()).thenReturn(frontPos);
        when(kitchen.getInteractable(frontPos)).thenReturn(frontInteractable);

        chefController.step(game, Set.of(GUI.ACTION.PLAYER1_SELECT),0);

        verify(frontInteractable,times(1)).interact(chef);
    }

    @Test
    void testChef2SelectWithNothingToInteract() throws IOException {
        Position frontPos = new Position(5,4);

        when(chef.getIndex()).thenReturn(1);
        when(chef.getFrontPosition()).thenReturn(frontPos);
        when(kitchen.getInteractable(frontPos)).thenReturn(null);

        chefController.step(game, Set.of(GUI.ACTION.PLAYER2_SELECT),0);

    }
}
