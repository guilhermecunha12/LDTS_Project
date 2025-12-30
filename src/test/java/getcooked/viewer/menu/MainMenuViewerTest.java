package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MainMenuViewerTest {
    private GUI gui;
    private MainMenu mainMenu;
    private MainMenuViewer mainMenuViewer;

    @BeforeEach
    void setUp() {
        gui = org.mockito.Mockito.mock(GUI.class);
        mainMenu = org.mockito.Mockito.mock(MainMenu.class);
        mainMenuViewer = new MainMenuViewer(mainMenu);
    }

    @Test
    void testDrawElementsSelectedFirst() {
        int x = 80;
        int y = 24;

        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);

        when(mainMenu.getNumberEntries()).thenReturn(3);
        when(mainMenu.getEntry(0)).thenReturn("Start Game");
        when(mainMenu.getEntry(1)).thenReturn("Settings");
        when(mainMenu.getEntry(2)).thenReturn("Exit");
        when(mainMenu.isSelected(0)).thenReturn(true);
        when(mainMenu.isSelected(1)).thenReturn(false);
        when(mainMenu.isSelected(2)).thenReturn(false);

        mainMenuViewer.drawElements(gui);

        verify(gui,times(1)).changeBackgroundColor("#468499");
        verify(gui,times(1)).paint();
        verify(gui,times(1)).drawText(new Position(x/2 -18, y/12), "Get Cooked", "#ffcf40");
        verify(gui,times(1)).drawText(new Position(x/2 -8, (y/2)-45), "Start Game", "#ffff00");
        verify(gui,times(1)).drawText(new Position(x/2 -15, (y/2) -35), "Settings", "#000000");
        verify(gui,times(1)).drawText(new Position(x/2 -7, (y/2) -25), "Exit", "#000000");
    }

    @Test
    void testDrawElementsSelectedSecond() {
        int x = 80;
        int y = 24;

        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);

        when(mainMenu.getNumberEntries()).thenReturn(3);
        when(mainMenu.getEntry(0)).thenReturn("Start Game");
        when(mainMenu.getEntry(1)).thenReturn("Settings");
        when(mainMenu.getEntry(2)).thenReturn("Exit");
        when(mainMenu.isSelected(0)).thenReturn(false);
        when(mainMenu.isSelected(1)).thenReturn(true);
        when(mainMenu.isSelected(2)).thenReturn(false);

        mainMenuViewer.drawElements(gui);

        verify(gui,times(1)).changeBackgroundColor("#468499");
        verify(gui,times(1)).paint();
        verify(gui,times(1)).drawText(new Position(x/2 -18, y/12), "Get Cooked", "#ffcf40");
        verify(gui,times(1)).drawText(new Position(x/2 -8, (y/2)-45), "Start Game", "#000000");
        verify(gui,times(1)).drawText(new Position(x/2 -15, (y/2) -35), "Settings", "#ffff00");
        verify(gui,times(1)).drawText(new Position(x/2 -7, (y/2) -25), "Exit", "#000000");
    }

    @Test
    void testDrawElementsSelectedThird() {
        int x = 80;
        int y = 24;

        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);

        when(mainMenu.getNumberEntries()).thenReturn(3);
        when(mainMenu.getEntry(0)).thenReturn("Start Game");
        when(mainMenu.getEntry(1)).thenReturn("Settings");
        when(mainMenu.getEntry(2)).thenReturn("Exit");
        when(mainMenu.isSelected(0)).thenReturn(false);
        when(mainMenu.isSelected(1)).thenReturn(false);
        when(mainMenu.isSelected(2)).thenReturn(true);

        mainMenuViewer.drawElements(gui);

        verify(gui,times(1)).changeBackgroundColor("#468499");
        verify(gui,times(1)).paint();
        verify(gui,times(1)).drawText(new Position(x/2 -18, y/12), "Get Cooked", "#ffcf40");
        verify(gui,times(1)).drawText(new Position(x/2 -8, (y/2)-45), "Start Game", "#000000");
        verify(gui,times(1)).drawText(new Position(x/2 -15, (y/2) -35), "Settings", "#000000");
        verify(gui,times(1)).drawText(new Position(x/2 -7, (y/2) -25), "Exit", "#ffff00");
    }
}