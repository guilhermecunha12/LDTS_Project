package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PotViewerTest {
    private GUI gui;
    private Pot pot;
    private PotViewer potViewer;

    @BeforeEach
    void setup() {
        gui = mock(GUI.class);
        pot = mock(Pot.class);
        potViewer = new PotViewer();
    }

    @Test
    void testDrawPotatoSoup() {
        Position position = mock(Position.class);
        Potato potato = mock(Potato.class);

        when(pot.getContent()).thenReturn(potato);
        when(pot.getPosition()).thenReturn(position);
        when(pot.isCooked()).thenReturn(true);

        potViewer.draw(pot, gui);

        verify(gui,times(1)).drawPot(position);
        verify(gui,times(1)).drawPotatoSoup(position);
    }

    @Test
    void testDrawCarrotSoup() {
        Position position = mock(Position.class);
        Carrot carrot = mock(Carrot.class);

        when(pot.getContent()).thenReturn(carrot);
        when(pot.getPosition()).thenReturn(position);
        when(pot.isCooked()).thenReturn(true);

        potViewer.draw(pot, gui);

        verify(gui,times(1)).drawPot(position);
        verify(gui,times(1)).drawCarrotSoup(position);
    }
}
