package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.CookMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CookMenuViewerTest {
    private CookMenuViewer cookMenuViewer;
    private CookMenu cookMenu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        cookMenu = mock(CookMenu.class);
        gui = mock(GUI.class);
        cookMenuViewer = new CookMenuViewer(cookMenu);
    }

    @Test
    void testDrawElements() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);

        when(cookMenu.getNumberEntries()).thenReturn(3);
        when(cookMenu.getEntry(0)).thenReturn("1 Player");
        when(cookMenu.getEntry(1)).thenReturn("2 Players");
        when(cookMenu.getEntry(2)).thenReturn("Back to Main Menu");
        when(cookMenu.isSelected(0)).thenReturn(true);
        when(cookMenu.isSelected(1)).thenReturn(false);
        when(cookMenu.isSelected(2)).thenReturn(false);

        cookMenuViewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();

        verify(gui,times(1)).drawText(new Position(x/4 - 5, y/2 - 15), "1 Player", "#ffff00");
        verify(gui,times(1)).drawText(new Position(3*x/4 - 30, y/2 - 15), "2 Players", "#000000");
        verify(gui,times(1)).drawText(new Position(x - 50, y - 35), "Back to Main Menu", "#000000");
    }

    @Test
    void testDrawElementsSecondSelected() {
        int x = 100;
        int y = 100;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);

        when(cookMenu.getNumberEntries()).thenReturn(3);
        when(cookMenu.getEntry(0)).thenReturn("1 Player");
        when(cookMenu.getEntry(1)).thenReturn("2 Players");
        when(cookMenu.getEntry(2)).thenReturn("Back to Main Menu");
        when(cookMenu.isSelected(0)).thenReturn(false);
        when(cookMenu.isSelected(1)).thenReturn(true);
        when(cookMenu.isSelected(2)).thenReturn(false);

        cookMenuViewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();

        verify(gui,times(1)).drawText(new Position(x/4 - 5, y/2 - 15), "1 Player", "#000000");
        verify(gui,times(1)).drawText(new Position(3*x/4 - 30, y/2 - 15), "2 Players", "#ffff00");
        verify(gui,times(1)).drawText(new Position(x - 50, y - 35), "Back to Main Menu", "#000000");
    }

    @Test
    void testDrawElementsThirdSelected() {
        int x = 100;
        int y = 100;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);

        when(cookMenu.getNumberEntries()).thenReturn(3);
        when(cookMenu.getEntry(0)).thenReturn("1 Player");
        when(cookMenu.getEntry(1)).thenReturn("2 Players");
        when(cookMenu.getEntry(2)).thenReturn("Back to Main Menu");
        when(cookMenu.isSelected(0)).thenReturn(false);
        when(cookMenu.isSelected(1)).thenReturn(false);
        when(cookMenu.isSelected(2)).thenReturn(true);

        cookMenuViewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();
        verify(gui,times(1)).drawText(new Position(x/4 - 5, y/2 - 15), "1 Player", "#000000");
        verify(gui,times(1)).drawText(new Position(3*x/4 - 30, y/2 - 15), "2 Players", "#000000");
        verify(gui,times(1)).drawText(new Position(x - 50, y - 35), "Back to Main Menu", "#ffff00");
    }
}

