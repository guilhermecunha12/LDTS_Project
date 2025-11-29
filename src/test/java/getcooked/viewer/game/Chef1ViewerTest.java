package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Dish;
import getcooked.model.game.elements.grabable.DishType;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Potato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class Chef1ViewerTest {
    private GUI gui;
    private Chef chef;
    private Chef1Viewer chef1Viewer;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.chef = mock(Chef.class);
        this.chef1Viewer = new Chef1Viewer();
    }

    @Test
    void testDrawPotato(){
        Potato potato = mock(Potato.class);
        Position position = mock(Position.class);

        when(chef.getItem()).thenReturn(potato);
        when(chef.getPosition()).thenReturn(position);

        chef1Viewer.draw(chef, gui);

        verify(gui,times(1)).drawChef1(position);
        verify(gui,times(1)).drawPotato(position);
    }

    @Test
    void testDrawPlateWithPotatoSoup(){
        Plate plate = mock(Plate.class);
        Position position = mock(Position.class);
        Dish dish = mock(Dish.class);

        when(chef.getItem()).thenReturn(plate);
        when(chef.getPosition()).thenReturn(position);
        when(plate.getContent()).thenReturn(dish);
        when(dish.getType()).thenReturn(DishType.POTATO_SOUP);

        chef1Viewer.draw(chef, gui);

        verify(gui,times(1)).drawPlate(position);
        verify(gui,times(1)).drawPotatoSoup(position);

    }
}
