package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.SettingsMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SettingsMenuViewerTest {
    private GUI gui;
    private SettingsMenu settingsMenu;
    private SettingsMenuViewer viewer;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.settingsMenu = mock(SettingsMenu.class);
        this.viewer = new SettingsMenuViewer(settingsMenu);
    }

    @Test
    void testDrawElementsSelectFirst() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);

        when(settingsMenu.getNumberEntries()).thenReturn(3);
        when(settingsMenu.getEntry(0)).thenReturn("Background Music ON");
        when(settingsMenu.getEntry(1)).thenReturn("Background Music Volume <75>");
        when(settingsMenu.getEntry(2)).thenReturn("Back");
        when(settingsMenu.isSelected(0)).thenReturn(true);
        when(settingsMenu.isSelected(1)).thenReturn(false);
        when(settingsMenu.isSelected(2)).thenReturn(false);

        viewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();
        verify(gui, times(1)).drawText(new Position(x/2 -35,y/2- 30), "Background Music ON", "#ffff00");
        verify(gui, times(1)).drawText(new Position(x/2 -52,y/2 -15), "Background Music Volume <75>", "#000000");
        verify(gui, times(1)).drawText(new Position(x - 50,y - 35), "Back", "#000000");
    }

    @Test
    void testDrawElementsSelectSecond() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);

        when(settingsMenu.getNumberEntries()).thenReturn(3);
        when(settingsMenu.getEntry(0)).thenReturn("Background Music ON");
        when(settingsMenu.getEntry(1)).thenReturn("Background Music Volume <75>");
        when(settingsMenu.getEntry(2)).thenReturn("Back");
        when(settingsMenu.isSelected(0)).thenReturn(false);
        when(settingsMenu.isSelected(1)).thenReturn(true);
        when(settingsMenu.isSelected(2)).thenReturn(false);

        viewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();
        verify(gui, times(1)).drawText(new Position(x/2 -35,y/2- 30), "Background Music ON", "#000000");
        verify(gui, times(1)).drawText(new Position(x/2 -52,y/2 -15), "Background Music Volume <75>", "#ffff00");
        verify(gui, times(1)).drawText(new Position(x - 50,y - 35), "Back", "#000000");
    }

    @Test
    void testDrawElementsSelectThird() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);

        when(settingsMenu.getNumberEntries()).thenReturn(3);
        when(settingsMenu.getEntry(0)).thenReturn("Background Music ON");
        when(settingsMenu.getEntry(1)).thenReturn("Background Music Volume <75>");
        when(settingsMenu.getEntry(2)).thenReturn("Back");
        when(settingsMenu.isSelected(0)).thenReturn(false);
        when(settingsMenu.isSelected(1)).thenReturn(false);
        when(settingsMenu.isSelected(2)).thenReturn(true);

        viewer.drawElements(gui);

        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();
        verify(gui, times(1)).drawText(new Position(x/2 -35,y/2- 30), "Background Music ON", "#000000");
        verify(gui, times(1)).drawText(new Position(x/2 -52,y/2 -15), "Background Music Volume <75>", "#000000");
        verify(gui, times(1)).drawText(new Position(x - 50,y - 35), "Back", "#ffff00");
    }
}
