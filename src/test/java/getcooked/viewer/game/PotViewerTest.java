package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.elements.interactable.cookware.Pot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PotViewerTest {
    private GUI gui;
    private Pot pot;
    private PotViewer potViewer;

    @BeforeEach
    public void setup() {
        gui = mock(GUI.class);
        pot = mock(Pot.class);
        potViewer = new PotViewer();
    }

    @Test
    public void testDraw() {
        Position position = mock(Position.class);

        when(pot.getPosition()).thenReturn(position);

        potViewer.draw(pot, gui);

        verify(gui,times(1)).drawPot(position);
    }
}
