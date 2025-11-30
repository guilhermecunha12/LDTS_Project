package getcooked.viewer.menu;
import getcooked.gui.GUI;
import getcooked.model.menu.GameOverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GameOverViewerTest {
    private GUI gui;
    private GameOverViewer gameOverViewer;
    private GameOverMenu gameOverMenu;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        gameOverMenu = mock(GameOverMenu.class);
        gameOverViewer = new GameOverViewer(gameOverMenu);
    }

    @Test
    void testDrawElements() {
        when(gui.getBackgroundColor()).thenReturn("#468499");
        int x = 50;
        int y = 50;
        when(gui.getWidth()).thenReturn(x);
        when(gui.getHeight()).thenReturn(y);
        when(gameOverMenu.getNumberEntries()).thenReturn(2);
        when(gameOverMenu.getEntry(0)).thenReturn("Play Again");
        when(gameOverMenu.getEntry(1)).thenReturn("Exit to Main Menu");
        when(gameOverMenu.isSelected(0)).thenReturn(true);
        when(gameOverMenu.isSelected(1)).thenReturn(false);
        gameOverViewer.drawElements(gui);
        verify(gui, times(1)).changeBackgroundColor("#468499");
        verify(gui, times(1)).paint();
        verify(gui, times(1)).drawText(new getcooked.model.Position((x / 2) - 4, y / 12), "Game Over", "#ffcf40", "#468499");
        verify(gui, times(1)).drawTextBox(new getcooked.model.Position((x / 2) - 6, 10), "Play Again", "#000000", "#ffff00", 1);
        verify(gui, times(1)).drawTextBox(new getcooked.model.Position((x / 2) - 9, 14), "Exit to Main Menu", "#000000", "#a9a9a9", 1);
    }
}
