package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.EscapeMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EscapeMenuViewerTest {
    private GUI gui;
    private EscapeMenu escapeMenu;
    private EscapeMenuViewer escapeMenuViewer;

    @BeforeEach
    void setUp() {
        this.escapeMenu = mock(EscapeMenu.class);
        this.gui = mock(GUI.class);
        this.escapeMenuViewer = new EscapeMenuViewer(escapeMenu);
    }

    @Test
    void testDrawElementsWithTwoEntriesFirstSelected() {
        int x = 100;
        int y = 100;

        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(escapeMenu.getNumberEntries()).thenReturn(2);
        when(escapeMenu.getEntry(0)).thenReturn("Resume Game");
        when(escapeMenu.getEntry(1)).thenReturn("Exit to Main Menu");
        when(escapeMenu.isSelected(0)).thenReturn(true);
        when(escapeMenu.isSelected(1)).thenReturn(false);

        escapeMenuViewer.drawElements(gui);

        verify(gui,times(1)).drawRectangle(new Position(x/ 2 - 90 / 2, y / 2 - 90 / 2), 90, 90, "#808080");
        verify(gui,times(1)).drawText(new Position(x / 2 - 90 / 4, y / 2 - 90 / 2 + 90 / 10), "Escape Menu", "#ffcf40");
        verify(gui,times(1)).drawText(new Position(x / 2 - 13, y / 2 - 10), "Resume Game", "#ffff00");
        verify(gui,times(1)).drawText(new Position(x / 2 - 32, y / 2), "Exit to Main Menu", "#ffffff");
    }

    @Test
    void testDrawElementsWithTwoEntriesSecondSelected() {
        int x = 200;
        int y = 200;

        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(escapeMenu.getNumberEntries()).thenReturn(2);
        when(escapeMenu.getEntry(0)).thenReturn("Resume Game");
        when(escapeMenu.getEntry(1)).thenReturn("Exit to Main Menu");
        when(escapeMenu.isSelected(0)).thenReturn(false);
        when(escapeMenu.isSelected(1)).thenReturn(true);

        escapeMenuViewer.drawElements(gui);

        verify(gui,times(1)).drawRectangle(new Position(x/ 2 - 90 / 2, y / 2 - 90 / 2), 90, 90, "#808080");
        verify(gui,times(1)).drawText(new Position(x / 2 - 90 / 4, y / 2 - 90 / 2 + 90 / 10), "Escape Menu", "#ffcf40");
        verify(gui,times(1)).drawText(new Position(x / 2 - 13, y / 2 - 10), "Resume Game", "#ffffff");
        verify(gui,times(1)).drawText(new Position(x / 2 - 32, y / 2), "Exit to Main Menu", "#ffff00");
    }
}