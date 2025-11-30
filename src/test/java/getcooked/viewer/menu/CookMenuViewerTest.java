package getcooked.viewer.menu;

import getcooked.gui.GUI;
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
        when(gui.getBackgroundColor()).thenReturn("#468499");
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
        verify(gui, times(1)).drawText(new getcooked.model.Position((x / 2) - 9, y / 12), "Get Ready To Cook", "#ffcf40", "#468499");
        verify(gui, times(1)).drawTextBox(new getcooked.model.Position((x / 4) - 5, y / 2), "1 Player", "#000000", "#ffff00", 1);
        verify(gui, times(1)).drawTextBox(new getcooked.model.Position((3 * x / 4) - 5, y / 2), "2 Players", "#000000", "#a9a9a9", 1);
        verify(gui, times(1)).drawTextBox(new getcooked.model.Position(x - 10, y - 3), "Back to Main Menu", "#000000", "#a9a9a9", 1);
    }
}
