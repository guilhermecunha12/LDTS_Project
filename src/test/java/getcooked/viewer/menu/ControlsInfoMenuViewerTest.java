package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.ControlsInfoMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ControlsInfoMenuViewerTest {

    private  ControlsInfoMenuViewer controlsInfoMenuViewer;
    private ControlsInfoMenu controlsInfoMenu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.controlsInfoMenu = mock(ControlsInfoMenu.class);
        this.controlsInfoMenuViewer = new ControlsInfoMenuViewer(controlsInfoMenu);
    }

    @Test
    void testDrawElementsOnePlayer() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(gui.getBackgroundColor()).thenReturn("#468499");
        when(controlsInfoMenu.isOnePlayer()).thenReturn(true);
        when(controlsInfoMenu.getEntry(0)).thenReturn("W: Move Up");
        when(controlsInfoMenu.getEntry(1)).thenReturn("A: Move Left");
        when(controlsInfoMenu.getEntry(2)).thenReturn("S: Move Down");
        when(controlsInfoMenu.getEntry(3)).thenReturn("D: Move Right");
        when(controlsInfoMenu.getEntry(4)).thenReturn("SPACE: Interact");
        controlsInfoMenuViewer.drawElements(gui);
        Position wPos = new Position(x/6, y/3);
        Position sPos = new Position(wPos.getX(), wPos.getY() + 3);
        Position aPos = new Position(sPos.getX() - 3, sPos.getY());
        Position dPos = new Position(sPos.getX() + 3, sPos.getY());
        Position spacePos = new Position(x/4, 2*y/3);
        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(2)).getBackgroundColor();
        verify(gui, times(1)).paint();
        verify(gui, times(1)).drawTextBox(wPos, controlsInfoMenu.getEntry(0), "#ffffff", "#000000", 1);
        verify(gui, times(1)).drawTextBox(aPos, controlsInfoMenu.getEntry(1), "#ffffff", "#000000", 1);
        verify(gui, times(1)).drawTextBox(sPos, controlsInfoMenu.getEntry(2), "#ffffff", "#000000", 1);
        verify(gui, times(1)).drawTextBox(dPos, controlsInfoMenu.getEntry(3), "#ffffff", "#000000", 1);
        verify(gui, times(1)).drawTextBox(spacePos, controlsInfoMenu.getEntry(4), "#ffffff", "#000000", 1);
    }

    @Test
    void testDrawElementsTwoPlayers() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(gui.getBackgroundColor()).thenReturn("#468499");
        when(controlsInfoMenu.isOnePlayer()).thenReturn(false);
        when(controlsInfoMenu.getEntry(0)).thenReturn("W: Move Up");
        when(controlsInfoMenu.getEntry(1)).thenReturn("A: Move Left");
        when(controlsInfoMenu.getEntry(2)).thenReturn("S: Move Down");
        when(controlsInfoMenu.getEntry(3)).thenReturn("D: Move Right");
        when(controlsInfoMenu.getEntry(4)).thenReturn("SPACE: Interact");
        when(controlsInfoMenu.getEntry(5)).thenReturn("UP: Move Up");
        when(controlsInfoMenu.getEntry(6)).thenReturn("LEFT: Move Left");
        when(controlsInfoMenu.getEntry(7)).thenReturn("DOWN: Move Down");
        when(controlsInfoMenu.getEntry(8)).thenReturn("RIGHT: Move Right");
        when(controlsInfoMenu.getEntry(9)).thenReturn("ENTER: Interact");
        controlsInfoMenuViewer.drawElements(gui);
        Position player1Pos = new Position(x/4 - 7, 6);
        Position player2Pos = new Position(3*x/4 - 8, 6);
        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(2)).getBackgroundColor();
        verify(gui, times(1)).paint();
        verify(gui, times(2)).drawText(player1Pos, "Player 1 (WASD)", "#ffffff", gui.getBackgroundColor());
        verify(gui, times(2)).drawText(player2Pos, "Player 2 (Arrows)", "#ffffff", gui.getBackgroundColor());
    }
}

