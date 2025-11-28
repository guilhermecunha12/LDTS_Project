package getcooked.controller.game;

import getcooked.model.Position;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.interactable.cookware.Cookware;
import getcooked.model.game.kitchen.elements.interactable.dispenser.Dispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.mockito.Mockito.*;

public class ChefController2Test {
    private Chef chef;
    private Kitchen kitchen;
    private ChefController2 chefController;

    @BeforeEach
    public void setUp() {
        this.chef = mock(Chef.class);
        this.kitchen = mock(Kitchen.class);
        this.chefController = new ChefController2(chef, kitchen);
    }

    @Test
    public void testMoveChefRight_WhenIsEmpty() {
        Position rightPos = mock(Position.class);
        Position currentPos = mock(Position.class);
        Chef kitchenChef1 = mock(Chef.class);
        Chef kitchenChef2 = mock(Chef.class);

        when(currentPos.getRight()).thenReturn(rightPos);
        when(kitchenChef2.getPosition()).thenReturn(currentPos);
        when(kitchen.getChefs()).thenReturn(List.of(kitchenChef1,kitchenChef2));
        when(kitchen.isEmpty(rightPos)).thenReturn(true);

        chefController.moveChefRight();

        verify(chef).setPosition(rightPos);
    }

    @Test
    public void testMoveChefRight_WhenNotEmpty() {
        Position rightPos = mock(Position.class);
        Position currentPos = mock(Position.class);
        Chef kitchenChef1 = mock(Chef.class);
        Chef kitchenChef2 = mock(Chef.class);

        when(currentPos.getRight()).thenReturn(rightPos);
        when(kitchenChef2.getPosition()).thenReturn(currentPos);
        when(kitchen.getChefs()).thenReturn(List.of(kitchenChef1,kitchenChef2));
        when(kitchen.isEmpty(rightPos)).thenReturn(false);

        chefController.moveChefRight();

        verify(chef,never()).setPosition(rightPos);
    }

    @Test
    void testChefSelect_interactWithDispenser() {
        Position posDown = mock(Position.class);
        Position posLeft = mock(Position.class);
        Position posUp = mock(Position.class);
        Position posRight = mock(Position.class);
        Position currentPos = mock(Position.class);

        Dispenser dispenser = mock(Dispenser.class);
        when(chef.getPosition()).thenReturn(currentPos);
        when(currentPos.getDown()).thenReturn(posDown);
        when(currentPos.getLeft()).thenReturn(posLeft);
        when(currentPos.getUp()).thenReturn(posUp);
        when(currentPos.getRight()).thenReturn(posRight);

        when(kitchen.getTile(posDown)).thenReturn(null);
        when(kitchen.getTile(posLeft)).thenReturn(null);
        when(kitchen.getTile(posUp)).thenReturn(null);
        when(kitchen.getTile(posRight)).thenReturn(dispenser);

        chefController.ChefSelect();

        verify(dispenser).interact(chef);
    }

    @Test
    void testChefSelect_interactWithCookware() {
        Position posDown = mock(Position.class);
        Position posLeft = mock(Position.class);
        Position posUp = mock(Position.class);
        Position posRight = mock(Position.class);
        Position currentPos = mock(Position.class);

        Cookware cookware= mock(Cookware.class);
        when(chef.getPosition()).thenReturn(currentPos);
        when(currentPos.getDown()).thenReturn(posDown);
        when(currentPos.getLeft()).thenReturn(posLeft);
        when(currentPos.getUp()).thenReturn(posUp);
        when(currentPos.getRight()).thenReturn(posRight);

        when(kitchen.getTile(posDown)).thenReturn(cookware);

        chefController.ChefSelect();

        verify(cookware).interact(chef);
    }

    //TODO: test step method
}
