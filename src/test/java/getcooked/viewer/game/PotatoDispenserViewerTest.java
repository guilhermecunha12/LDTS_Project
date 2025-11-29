package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PotatoDispenserViewerTest {
    private GUI gui;
    private PotatoDispenser potatoDispenser;
    private PotatoDispenserViewer potatoDispenserViewer;


    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        potatoDispenser = mock(PotatoDispenser.class);
        potatoDispenserViewer = new PotatoDispenserViewer();
    }

    @Test
    void testDraw() {
        Position position = mock(Position.class);

        when(potatoDispenser.getPosition()).thenReturn(position);

        potatoDispenserViewer.draw(potatoDispenser, gui);

        verify(gui,times(1)).drawPotatoDispenser(position);
    }
}
