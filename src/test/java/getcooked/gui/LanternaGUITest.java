package getcooked.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import getcooked.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LanternaGUITest {

    private TerminalSize terminalSize;
    private TextGraphics textGraphics;
    private Screen screen;
    private InputManager inputManager;
    private FontManager fontManager;
    private String backgroundColor;
    private ScreenCreator screenCreator;
    private Position position;
    private String text;
    private String color;

    @BeforeEach
    void setUp(){
        this.terminalSize = mock(TerminalSize.class);
        this.screen = mock(Screen.class);
        this.inputManager = mock(InputManager.class);
        this.fontManager = mock(FontManager.class);
        this.backgroundColor = "#ffffff";
        this.screenCreator = mock(ScreenCreator.class);
        this.position = mock(Position.class);
        this.text = "te";
        this.color = "#ffffff";
        this.textGraphics = mock(TextGraphics.class);
    }

    @Test
    void testGetHeight() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(terminalSize.getRows()).thenReturn(1);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        assertEquals(1, gui.getHeight());
    }

    @Test
    void testGetWidth() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(terminalSize.getColumns()).thenReturn(1);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        assertEquals(1, gui.getWidth());
    }

    @Test
    void testGetNextAction() throws IOException {
        when(inputManager.getNextAction()).thenReturn(Set.of(GUI.ACTION.PLAYER1_UP));
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        Set<GUI.ACTION> actions =  gui.getNextAction();
        assertEquals(Set.of(GUI.ACTION.PLAYER1_UP), actions);

    }

    @Test
    void testDrawTextNonCached() throws IOException {
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.drawText(position, text, color);
        // Considering that the text length is 2
        verify(fontManager, times(2)).getCharPositionCache();
        verify(fontManager, times(2)).getFontImage();
    }

    @Test
    void testDrawTextCached() throws IOException {
        this.text = "t";
        Character c = 't';
        when(screenCreator.createScreen()).thenReturn(screen);
        when(fontManager.getCharPositionCache()).thenReturn(Map.of(c, new Coordinate(0,0)));
        BufferedImage bufferedImage = new BufferedImage(3,5, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.setRGB(1, 1, 0xFFFFFFFF); // one single white pixel
        when(fontManager.getFontImage()).thenReturn(bufferedImage);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.drawText(position, text, color);
        verify(screen, times(14)).newTextGraphics();
    }

    @Test
    void testDrawRectangle() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.drawRectangle(position, 2, 2, color);
        verify(screen, times(1)).newTextGraphics();
        verify(textGraphics, times(1)).setBackgroundColor(any());
    }

    @Test
    void testDrawPixel() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.drawPixel(0, 0, TextColor.Factory.fromString(backgroundColor));
        verify(screen, times(1)).newTextGraphics();
        verify(textGraphics, times(1))
                .setBackgroundColor(TextColor.Factory.fromString(any()));
        verify(textGraphics, times(1)).setCharacter(0, 0, ' ');
    }

    @Test
    void testChangeBackgroundColor() throws IOException {
        String newColor = "#000000";
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.changeBackgroundColor(newColor);
        assertEquals(newColor, gui.getBackgroundColor());

    }

    @Test
    void testPaint() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.paint();
        verify(textGraphics, times(1)).setBackgroundColor(any());
        verify(textGraphics, times(1)).fill(' ');
    }

    @Test
    void testClear() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.clear();
        verify(screen, times(1)).clear();
    }

    @Test
    void testRefresh() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.refresh();
        verify(screen, times(1)).refresh(Screen.RefreshType.DELTA);
    }

    @Test
    void testClose() throws IOException {
        when(screenCreator.createScreen()).thenReturn(screen);
        LanternaGUI gui = new LanternaGUI(screenCreator, fontManager, inputManager);
        gui.close();
        verify(screen, times(1)).close();
    }
}