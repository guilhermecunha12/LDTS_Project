package getcooked.controller.game;

import getcooked.Game;
import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.interactable.Counter;
import getcooked.model.game.kitchen.elements.interactable.DeliverySpot;
import getcooked.model.game.kitchen.elements.interactable.Trash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

public class ChefController1Test {
    private Chef chef;
    private ChefController1 chefController;
    private Kitchen kitchen;
    private Game game;

    @BeforeEach
    void setUp() {
        this.kitchen = mock(Kitchen.class);
        this.chef = mock(Chef.class);
        this.game = mock(Game.class);
        this.chefController = new ChefController1(chef, kitchen);
    }

    @Test
    void testMoveChefUp_whenEmpty() {
        Position upPos = mock(Position.class);
        Position currentPos = mock(Position.class);
        Chef kitchenChef = mock(Chef.class);

        when(currentPos.getUp()).thenReturn(upPos);
        when(kitchenChef.getPosition()).thenReturn(currentPos);
        when(kitchen.getChefs()).thenReturn(List.of(kitchenChef));
        when(kitchen.isEmpty(upPos)).thenReturn(true);

        chefController.moveChefUp();

        verify(chef).setPosition(upPos);
    }

    @Test
    void testMoveChefUp_whenNotEmpty() {
        Position upPos = mock(Position.class);
        Position currentPos = mock(Position.class);
        Chef kitchenChef = mock(Chef.class);

        when(currentPos.getUp()).thenReturn(upPos);
        when(kitchenChef.getPosition()).thenReturn(currentPos);
        when(kitchen.getChefs()).thenReturn(List.of(kitchenChef));
        when(kitchen.isEmpty(upPos)).thenReturn(false);

        chefController.moveChefUp();

        // will never call this method
        verify(chef, never()).setPosition(currentPos);
    }

    @Test
    void testChefSelect_interactWithDeliverySpot() {
        Position posDown = mock(Position.class);
        Position posLeft = mock(Position.class);
        Position posUp = mock(Position.class);
        Position posRight = mock(Position.class);
        Position currentPos = mock(Position.class);

        DeliverySpot deliverySpot = mock(DeliverySpot.class);
        when(chef.getPosition()).thenReturn(currentPos);
        when(currentPos.getDown()).thenReturn(posDown);
        when(currentPos.getLeft()).thenReturn(posLeft);
        when(currentPos.getUp()).thenReturn(posUp);
        when(currentPos.getRight()).thenReturn(posRight);

        when(kitchen.getTile(posDown)).thenReturn(null);
        when(kitchen.getTile(posLeft)).thenReturn(null);
        when(kitchen.getTile(posUp)).thenReturn(null);
        when(kitchen.getTile(posRight)).thenReturn(deliverySpot);

        chefController.ChefSelect();

        verify(deliverySpot).interact(chef, kitchen);
    }

    @Test
    void testChefSelect_interactWithCounter() {
        Position posDown = mock(Position.class);
        Position posLeft = mock(Position.class);
        Position posUp = mock(Position.class);
        Position posRight = mock(Position.class);
        Position currentPos = mock(Position.class);

        Counter counter = mock(Counter.class);
        when(chef.getPosition()).thenReturn(currentPos);
        when(currentPos.getDown()).thenReturn(posDown);
        when(currentPos.getLeft()).thenReturn(posLeft);
        when(currentPos.getUp()).thenReturn(posUp);
        when(currentPos.getRight()).thenReturn(posRight);

        when(kitchen.getTile(posDown)).thenReturn(counter);

        chefController.ChefSelect();

        verify(counter).interact(chef);
    }

    @Test
    void testChefSelect_interactWithTrash() {
        Position posDown = mock(Position.class);
        Position posLeft = mock(Position.class);
        Position posUp = mock(Position.class);
        Position posRight = mock(Position.class);
        Position currentPos = mock(Position.class);

        Trash trash = mock(Trash.class);
        when(chef.getPosition()).thenReturn(currentPos);
        when(currentPos.getDown()).thenReturn(posDown);
        when(currentPos.getLeft()).thenReturn(posLeft);
        when(currentPos.getUp()).thenReturn(posUp);
        when(currentPos.getRight()).thenReturn(posRight);

        when(kitchen.getTile(posDown)).thenReturn(null);
        when(kitchen.getTile(posLeft)).thenReturn(trash);

        chefController.ChefSelect();

        verify(trash).interact(chef);
    }

    @Test
    void testChefStep() throws IOException {
        Position posDown = mock(Position.class);
        Position posLeft = mock(Position.class);
        Position posUp = mock(Position.class);
        Position posRight = mock(Position.class);
        Position currentPos = mock(Position.class);
        Chef kitchenChef = mock(Chef.class);

        when(chef.getPosition()).thenReturn(currentPos);
        when(currentPos.getDown()).thenReturn(posDown);
        when(currentPos.getLeft()).thenReturn(posLeft);
        when(currentPos.getUp()).thenReturn(posUp);
        when(currentPos.getRight()).thenReturn(posRight);

        when(kitchenChef.getPosition()).thenReturn(currentPos);
        when(kitchen.getChefs()).thenReturn(List.of(kitchenChef));

        when(kitchen.isEmpty(posUp)).thenReturn(true);
        when(kitchen.isEmpty(posRight)).thenReturn(true);
        when(kitchen.isEmpty(posDown)).thenReturn(true);
        when(kitchen.isEmpty(posLeft)).thenReturn(true);

        Set<GUI.ACTION> actions = Set.of(
                GUI.ACTION.PLAYER1_UP,
                GUI.ACTION.PLAYER1_DOWN,
                GUI.ACTION.PLAYER1_LEFT,
                GUI.ACTION.PLAYER1_RIGHT
        );

        chefController.step(game, actions, 0);

        verify(chef).setPosition(posUp);
        verify(chef).setPosition(posDown);
        verify(chef).setPosition(posLeft);
        verify(chef).setPosition(posRight);
        verify(chef, times(4)).setPosition(any());
    }
}
