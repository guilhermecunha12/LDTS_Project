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

    private EscapeMenuViewer viewer;

    @BeforeEach
    void setUp() {
        this.escapeMenu = mock(EscapeMenu.class);
        this.gui = mock(GUI.class);
        this.viewer = new EscapeMenuViewer(escapeMenu);

    }

    @Test
    void testDrawElementsWithTwoEntriesFirstSelected() {
        int guiWidth = 100;
        int guiHeight = 100;
        when(gui.getWidth()).thenReturn(guiWidth);
        when(gui.getHeight()).thenReturn(guiHeight);

        when(escapeMenu.getNumberEntries()).thenReturn(2);
        when(escapeMenu.getEntry(0)).thenReturn("Resume Game");
        when(escapeMenu.getEntry(1)).thenReturn("Exit to Main Menu");
        when(escapeMenu.isSelected(0)).thenReturn(true);
        when(escapeMenu.isSelected(1)).thenReturn(false);

        viewer.drawElements(gui);
        // Calculate expected positions (like the viewer would)
        int side = 21;
        String rectangleColor = "#808080";

        // Hardcoded rectangle position
        int rectX = guiWidth / 2 - side / 2;
        int rectY = guiHeight / 2 - side / 2;
        Position expectedRectPos = new Position(rectX, rectY);

        // Hardcoded title position
        int titleX = guiWidth / 2 - 5;
        int titleY = guiHeight / 2 - side / 2 + side / 10;
        Position expectedTitlePos = new Position(titleX, titleY);

        // Entry 0 position
        String entry0 = "Resume Game";
        int entry0X = guiWidth / 2 - entry0.length() / 2;
        int entry0Y = guiHeight / 2 - side / 2 + 4 * side / 10;
        Position expectedEntry0Pos = new Position(entry0X, entry0Y);

        // Entry 1 position
        String entry1 = "Exit to Main Menu";
        int entry1X = guiWidth / 2 - entry1.length() / 2;
        int entry1Y = guiHeight / 2 - side / 2 + 4 * side / 10 + 4;
        Position expectedEntry1Pos = new Position(entry1X, entry1Y);

        verify(gui).drawRectangle(expectedRectPos, side, side, rectangleColor);
        verify(gui).drawText(expectedTitlePos, "Escape Menu", "#ffcf40", rectangleColor);
        verify(gui).drawText(expectedEntry0Pos, entry0, "#ffff00", rectangleColor);
        verify(gui).drawText(expectedEntry1Pos, entry1, "#ffffff", rectangleColor);
    }

    @Test
    void testDrawElementsWithTwoEntriesSecondSelected() {
        // Setup: GUI dimensions
        int guiWidth = 100;
        int guiHeight = 100;
        when(gui.getWidth()).thenReturn(guiWidth);
        when(gui.getHeight()).thenReturn(guiHeight);

        // Setup: Menu entries
        when(escapeMenu.getNumberEntries()).thenReturn(2);
        when(escapeMenu.getEntry(0)).thenReturn("Resume Game");
        when(escapeMenu.getEntry(1)).thenReturn("Exit to Main Menu");
        when(escapeMenu.isSelected(0)).thenReturn(false);
        when(escapeMenu.isSelected(1)).thenReturn(true);

        // Call the method
        viewer.drawElements(gui);

        // Calculate expected positions
        int side = 21;
        String rectangleColor = "#808080";

        int rectX = guiWidth / 2 - side / 2;
        int rectY = guiHeight / 2 - side / 2;
        Position expectedRectPos = new Position(rectX, rectY);

        int titleX = guiWidth / 2 - 5;
        int titleY = guiHeight / 2 - side / 2 + side / 10;
        Position expectedTitlePos = new Position(titleX, titleY);

        String entry0 = "Resume Game";
        int entry0X = guiWidth / 2 - entry0.length() / 2;
        int entry0Y = guiHeight / 2 - side / 2 + 4 * side / 10;
        Position expectedEntry0Pos = new Position(entry0X, entry0Y);

        String entry1 = "Exit to Main Menu";
        int entry1X = guiWidth / 2 - entry1.length() / 2;
        int entry1Y = guiHeight / 2 - side / 2 + 4 * side / 10 + 4;
        Position expectedEntry1Pos = new Position(entry1X, entry1Y);

        verify(gui).drawRectangle(expectedRectPos, side, side, rectangleColor);
        verify(gui).drawText(expectedTitlePos, "Escape Menu", "#ffcf40", rectangleColor);
        verify(gui).drawText(expectedEntry0Pos, entry0, "#ffffff", rectangleColor);
        verify(gui).drawText(expectedEntry1Pos, entry1, "#ffff00", rectangleColor);
    }
}
