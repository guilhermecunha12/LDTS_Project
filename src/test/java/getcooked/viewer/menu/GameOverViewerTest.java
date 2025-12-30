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
    void testDrawFirstSelected() {
        int x = 50;
        int y = 50;

        when(gui.getHeight()).thenReturn(y);
        when(gui.getWidth()).thenReturn(x);
        when(gameOverMenu.getNumberEntries()).thenReturn(2);
        when(gameOverMenu.getEntry(0)).thenReturn("Back to Main Menu");
        when(gameOverMenu.getEntry(1)).thenReturn("Exit");
        when(gameOverMenu.isSelected(0)).thenReturn(true);
        when(gameOverMenu.isSelected(1)).thenReturn(false);
        when(gameOverMenu.getOrdersDelivered()).thenReturn(5);

        gameOverViewer.drawElements(gui);

        verify(gui,times(1)).changeBackgroundColor("#468499");
        verify(gui,times(1)).paint();
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 18, y / 12), "Game Over", "#ffcf40");
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 35, y / 6 + 5), "Delivered dishes: 5", "#ffcf40");
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 32, y / 2 - 10), "Back to Main Menu", "#ffff00");
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 17, y / 2), "Exit", "#000000");
    }

    @Test
    void testDrawSecondSelected() {
        int x = 80;
        int y = 80;

        when(gui.getHeight()).thenReturn(y);
        when(gui.getWidth()).thenReturn(x);
        when(gameOverMenu.getNumberEntries()).thenReturn(2);
        when(gameOverMenu.getEntry(0)).thenReturn("Back to Main Menu");
        when(gameOverMenu.getEntry(1)).thenReturn("Exit");
        when(gameOverMenu.isSelected(0)).thenReturn(false);
        when(gameOverMenu.isSelected(1)).thenReturn(true);
        when(gameOverMenu.getOrdersDelivered()).thenReturn(10);

        gameOverViewer.drawElements(gui);

        verify(gui,times(1)).changeBackgroundColor("#468499");
        verify(gui,times(1)).paint();
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 18, y / 12), "Game Over", "#ffcf40");
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 35, y / 6 + 5), "Delivered dishes: 10", "#ffcf40");
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 32, y / 2 - 10), "Back to Main Menu", "#000000");
        verify(gui,times(1)).drawText(new getcooked.model.Position(x / 2 - 17, y / 2), "Exit", "#ffff00");
    }
}