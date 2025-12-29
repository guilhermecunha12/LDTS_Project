package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.ControlsInfoMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class ControlsInfoMenuViewerTest {
    private  ControlsInfoMenuViewer controlsInfoMenuViewer;
    private ControlsInfoMenu controlsInfoMenu;
    private GUI gui;
    private static final String TEXT_COLOR = "#ffffff";

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.controlsInfoMenu = mock(ControlsInfoMenu.class);
        this.controlsInfoMenuViewer = new ControlsInfoMenuViewer(controlsInfoMenu);
    }

    @Test
    void testDrawElements1Player() throws IOException, URISyntaxException, FontFormatException {
        int x = 50;
        int y = 50;

        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(controlsInfoMenu.isOnePlayer()).thenReturn(true);

        when(controlsInfoMenu.getEntry(0)).thenReturn("W - move up");
        when(controlsInfoMenu.getEntry(1)).thenReturn("A - move left");
        when(controlsInfoMenu.getEntry(2)).thenReturn("S - move down");
        when(controlsInfoMenu.getEntry(3)).thenReturn("D - move right");
        when(controlsInfoMenu.getEntry(4)).thenReturn("Space - grab/drop");

        controlsInfoMenuViewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();
        verify(gui,times(1)).drawText(new Position(x/2 -16, y/12), "Controls", "#ffcf40");
        verify(gui,times(1)).drawText(new Position(1, 1), "ESC - Escape Menu", TEXT_COLOR);
        verify(gui,times(1)).drawText(new Position(x/2+30,y/2 -30), "W - move up", TEXT_COLOR);
        verify(gui,times(1)).drawText(new Position(x/2+30,y/2 -22), "S - move down", TEXT_COLOR);
        verify(gui,times(1)).drawText(new Position(x/2+30,y/2 -14), "A - move left", TEXT_COLOR);
        verify(gui,times(1)).drawText(new Position(x/2+30,y/2 -6), "D - move right", TEXT_COLOR);
        verify(gui,times(1)).drawText(new Position(x/2+30,y/2 +2), "Space - grab/drop", TEXT_COLOR);
    }

    @Test
    void testDrawElements2Player() throws IOException, URISyntaxException, FontFormatException {
        int x = 120;
        int y = 120;

        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(controlsInfoMenu.isOnePlayer()).thenReturn(false);

        when(controlsInfoMenu.getEntry(0)).thenReturn("W - move up");
        when(controlsInfoMenu.getEntry(1)).thenReturn("A - move left");
        when(controlsInfoMenu.getEntry(2)).thenReturn("S - move down");
        when(controlsInfoMenu.getEntry(3)).thenReturn("D - move right");
        when(controlsInfoMenu.getEntry(4)).thenReturn("Space - grab/drop");
        when(controlsInfoMenu.getEntry(5)).thenReturn("UP - move up");
        when(controlsInfoMenu.getEntry(6)).thenReturn("LEFT- move left");
        when(controlsInfoMenu.getEntry(7)).thenReturn("DOWN - move down");
        when(controlsInfoMenu.getEntry(8)).thenReturn("RIGHT - move right");
        when(controlsInfoMenu.getEntry(9)).thenReturn("ENTER - grab/drop");

        controlsInfoMenuViewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();
        verify(gui,times(1)).drawText(new Position(x/2 -16, y/12), "Controls", "#ffcf40");
        verify(gui,times(1)).drawText(new Position(1, 1), "ESC - Escape Menu", TEXT_COLOR);
        // Player 1
        verify(gui).drawText(new Position(x/2 -100, y-70), "W - move up", TEXT_COLOR);
        verify(gui).drawText(new Position(x/2 -100, y-62), "S - move down", TEXT_COLOR);
        verify(gui).drawText(new Position(x/2 -100, y-54), "A - move left", TEXT_COLOR);
        verify(gui).drawText(new Position(x/2 -100, y-46), "D - move right", TEXT_COLOR);
        verify(gui).drawText(new Position(x/2 -100, y-38), "Space - grab/drop", TEXT_COLOR);

        // Player 2
        verify(gui).drawText(new Position(x/2 +45, y-70), "UP - move up", TEXT_COLOR);
        verify(gui).drawText(new Position(x/2 +45, y-54), "LEFT- move left", TEXT_COLOR); // correct mapping
        verify(gui).drawText(new Position(x/2 +45, y-62), "DOWN - move down", TEXT_COLOR);
        verify(gui).drawText(new Position(x/2 +45, y-46), "RIGHT - move right", TEXT_COLOR);
        verify(gui).drawText(new Position(x/2 +45, y-38), "ENTER - grab/drop", TEXT_COLOR);

    }

}

