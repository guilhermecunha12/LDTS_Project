package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MainMenuViewerTest {
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
    void testDrawElementsSelected0() {
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);
        when(gui.getBackgroundColor()).thenReturn("#468499");

        when(mainMenu.getNumberEntries()).thenReturn(2);
        when(mainMenu.getEntry(0)).thenReturn("Start Game");
        when(mainMenu.getEntry(1)).thenReturn("Exit");
        when(mainMenu.isSelected(0)).thenReturn(true);
        when(mainMenu.isSelected(1)).thenReturn(false);

        mainMenuViewer.drawElements(gui);

        verify(gui,times(1)).changeBackgroundColor("#468499");
        verify(gui,times(1)).paint();
        verify(gui,times(1)).drawText(new getcooked.model.Position(35, 2), "Get Cooked", "#ffcf40", "#468499");
        verify(gui,times(1)).drawTextBox(new getcooked.model.Position(34, 10), "Start Game", "#000000", "#ffff00", 1);
        verify(gui,times(1)).drawTextBox(new getcooked.model.Position(37, 14), "Exit", "#000000", "#a9a9a9", 1);
    }

    @Test
    void testDrawElementsSelected1() {
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);
        when(gui.getBackgroundColor()).thenReturn("#468499");

        when(mainMenu.getNumberEntries()).thenReturn(2);
        when(mainMenu.getEntry(0)).thenReturn("Start Game");
        when(mainMenu.getEntry(1)).thenReturn("Exit");
        when(mainMenu.isSelected(0)).thenReturn(false);
        when(mainMenu.isSelected(1)).thenReturn(true);

        mainMenuViewer.drawElements(gui);

        verify(gui,times(1)).changeBackgroundColor("#468499");
        verify(gui,times(1)).paint();
        verify(gui,times(1)).drawText(new getcooked.model.Position(35, 2), "Get Cooked", "#ffcf40", "#468499");
        verify(gui,times(1)).drawTextBox(new getcooked.model.Position(34, 10), "Start Game", "#000000", "#a9a9a9", 1);
        verify(gui,times(1)).drawTextBox(new getcooked.model.Position(37, 14), "Exit", "#000000", "#ffff00", 1);
    }
}
