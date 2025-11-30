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
    void testDrawElementsSelectedSettings() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(gui.getBackgroundColor()).thenReturn("#468499");

        // Setup menu entries - must match what the viewer expects
        when(settingsMenu.getNumberEntries()).thenReturn(2);  // 1 setting + 1 back button
        when(settingsMenu.getEntry(0)).thenReturn("Background Music ON");
        when(settingsMenu.getEntry(1)).thenReturn("Back");
        when(settingsMenu.isSelected(0)).thenReturn(true);   // Setting selected
        when(settingsMenu.isSelected(1)).thenReturn(false);  // Back not selected

        // Call the method
        viewer.drawElements(gui);

        // Verify background setup
        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();

        // Verify title
        Position posTitle = new Position(x / 2 - 4, y / 12);
        verify(gui, times(1)).drawText(posTitle, "Settings", "#ffcf40", "#468499");

        // Verify settings option (selected = yellow)
        Position posSetting = new Position(5, 10);
        verify(gui, times(1)).drawText(posSetting, "Background Music ON", "#ffff00", "#468499");

        // Verify back button (not selected = gray)
        Position posBack = new Position(x - 10, y - 3);
        verify(gui, times(1)).drawTextBox(posBack, "Back", "#000000", "#a9a9a9", 1);
    }

    @Test
    void testDrawElementsSelectedBack() {
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(gui.getBackgroundColor()).thenReturn("#468499");

        // Setup menu entries
        when(settingsMenu.getNumberEntries()).thenReturn(2);  // 1 setting + 1 back button
        when(settingsMenu.getEntry(0)).thenReturn("Background Music ON");
        when(settingsMenu.getEntry(1)).thenReturn("Back");
        when(settingsMenu.isSelected(0)).thenReturn(false);  // Setting not selected
        when(settingsMenu.isSelected(1)).thenReturn(true);   // Back selected

        // Call the method
        viewer.drawElements(gui);

        // Verify background setup
        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();

        // Verify title
        Position posTitle = new Position(x / 2 - 4, y / 12);
        verify(gui, times(1)).drawText(posTitle, "Settings", "#ffcf40", "#468499");

        // Verify settings option (not selected = black)
        Position posSetting = new Position(5, 10);
        verify(gui, times(1)).drawText(posSetting, "Background Music ON", "#000000", "#468499");

        // Verify back button (selected = yellow)
        Position posBack = new Position(x - 10, y - 3);
        verify(gui, times(1)).drawTextBox(posBack, "Back", "#000000", "#ffff00", 1);
    }
}