package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.elements.grabable.Dish;
import getcooked.model.game.elements.grabable.DishType;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.Counter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CounterViewerTest {
    private GUI gui;
    private Counter counter;
    private CounterViewer counterViewer;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.counter = mock(Counter.class);
        this.counterViewer = new CounterViewer();
    }

    @Test
    void testDrawCounterWithPotato(){
        Potato potato = mock(Potato.class);
        Position position = mock(Position.class);

        when(counter.getItem()).thenReturn(potato);
        when(counter.getPosition()).thenReturn(position);

        counterViewer.draw(counter, gui);
        verify(gui,times(1)).drawCounter(position);
        verify(gui,times(1)).drawPotato(position);
    }

    @Test
    void testDrawCounterWithPlateWithPotatoSoup(){
        Plate plate = mock(Plate.class);
        Dish dish = mock(Dish.class);
        Position position = mock(Position.class);

        when(counter.getItem()).thenReturn(plate);
        when(counter.getPosition()).thenReturn(position);
        when(plate.getContent()).thenReturn(dish);
        when(dish.getType()).thenReturn(DishType.POTATO_SOUP);

        counterViewer.draw(counter, gui);
        verify(gui,times(1)).drawPlate(position);
        verify(gui,times(1)).drawPotatoSoup(position);
    }
}
