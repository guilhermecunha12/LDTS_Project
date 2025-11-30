package getcooked.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import getcooked.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GUITest {
    private static final Map<Integer, GUI.ACTION> KEY_MAPPINGS = Map.ofEntries(
            // PLAYER 1
            Map.entry(KeyEvent.VK_W, GUI.ACTION.PLAYER1_UP),
            Map.entry(KeyEvent.VK_A, GUI.ACTION.PLAYER1_LEFT),
            Map.entry(KeyEvent.VK_S, GUI.ACTION.PLAYER1_DOWN),
            Map.entry(KeyEvent.VK_D, GUI.ACTION.PLAYER1_RIGHT),
            Map.entry(KeyEvent.VK_SPACE, GUI.ACTION.PLAYER1_SELECT),
            // PLAYER 2
            Map.entry(KeyEvent.VK_UP, GUI.ACTION.PLAYER2_UP),
            Map.entry(KeyEvent.VK_LEFT, GUI.ACTION.PLAYER2_LEFT),
            Map.entry(KeyEvent.VK_DOWN, GUI.ACTION.PLAYER2_DOWN),
            Map.entry(KeyEvent.VK_RIGHT, GUI.ACTION.PLAYER2_RIGHT),
            Map.entry(KeyEvent.VK_ENTER, GUI.ACTION.PLAYER2_SELECT),
            // ESCAPE
            Map.entry(KeyEvent.VK_ESCAPE, GUI.ACTION.ESCAPE)
    );

    private LanternaGUI lanternaGUI;
    private Set<Integer> activeInputs;
    private Set<Integer> previousInputs;
    private Map<Integer, Long> lastActionTime;
    private String backgroundColor;
    private Screen mockScreen;
    private TerminalSize mockTerminalSize;
    private TextGraphics mockTextGraphics;

    @BeforeEach
    void setUp() {
        this.activeInputs = new HashSet<>();
        this.previousInputs = new HashSet<>();
        this.lastActionTime = new HashMap<>();
        this.backgroundColor = "#ffffff";

        // Mocking the dependencies inside LanternaGUI
        this.mockScreen = mock(Screen.class);
        this.mockTerminalSize = mock(TerminalSize.class);
        this.mockTextGraphics = mock(TextGraphics.class);
        // Inside the LanternaGUI constructor replace the Screen, TerminalSize, and TextGraphics with mocks
        when(mockScreen.getTerminalSize()).thenReturn(mockTerminalSize);
        when(mockTerminalSize.getRows()).thenReturn(20);
        when(mockTerminalSize.getColumns()).thenReturn(20);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        // The screen inside lanterna will be a mock
        this.lanternaGUI = new LanternaGUI(mockScreen);
    }

    @Test
    void testTryProcessKeyKeyCodeJustPressed() {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        // Use the custom constructor
        LanternaGUI gui = new LanternaGUI(20, 20, activeInputs, previousInputs, lastActionTime, backgroundColor);
        long currentTime = 1L;
        gui.tryProcessKey(keyCode, expectedAction, currentTime , actions);
        assertEquals(true, actions.contains(expectedAction));
        assertEquals(currentTime, lastActionTime.get(keyCode));
    }


    @Test
    void testTryProcessKeyKeyCodeAlreadyPressedLastActionNull() {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        previousInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        LanternaGUI gui = new LanternaGUI(20, 20, activeInputs, previousInputs, lastActionTime, backgroundColor);
        gui.tryProcessKey(keyCode, expectedAction, System.currentTimeMillis(), actions);
        assertEquals(false, actions.contains(expectedAction));
    }

    @Test
    void testTryProcessKeyKeyCodeAlreadyPressedLastActionNotNullValidThreshold()  {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        previousInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        LanternaGUI gui = new LanternaGUI(20, 20, activeInputs, previousInputs, lastActionTime, backgroundColor);
        // Bigger time than the MIN_HOLD_TIME
        long pastTime = System.currentTimeMillis() - 500; // 500 ms ago
        lastActionTime.put(keyCode, pastTime);
        Long currentTime = System.currentTimeMillis();
        gui.tryProcessKey(keyCode, expectedAction, currentTime, actions);
        assertEquals(true, actions.contains(expectedAction));
        assertEquals(currentTime, lastActionTime.get(keyCode));
    }

    @Test
    void testTryProcessKeyKeyCodeAlreadyPressedLastActionNotNullInvalidThreshold()  {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        previousInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        LanternaGUI gui = new LanternaGUI(20, 20, activeInputs, previousInputs, lastActionTime, backgroundColor);
        Long currentTime = System.currentTimeMillis();
        // Smaller time than the MIN_HOLD_TIME
        Long pastTime = System.currentTimeMillis() - 30;
        lastActionTime.put(keyCode, pastTime);
        gui.tryProcessKey(keyCode, expectedAction, currentTime, actions);
        assertEquals(false, actions.contains(expectedAction));
    }

    @Test
    void testTryProcessKeyKeyCodeNotPressed() {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        LanternaGUI gui = new LanternaGUI(20, 20, activeInputs, previousInputs, lastActionTime, backgroundColor);
        gui.tryProcessKey(keyCode, expectedAction, System.currentTimeMillis(), actions);
        assertEquals(false, actions.contains(expectedAction));
    }

    @Test
    void testgetNextActionValidAction()  {
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        this.lanternaGUI = new LanternaGUI(20, 20, activeInputs, previousInputs, lastActionTime, backgroundColor);
        Set<GUI.ACTION> actions = lanternaGUI.getNextAction();
        assertTrue(actions.contains(expectedAction));
    }

    @Test
    void testgetBackgroundColor(){
        String expectedColor = this.backgroundColor;
        String actualColor = this.lanternaGUI.getBackgroundColor();
        assertEquals(expectedColor, actualColor);
    }

    @Test
    void testgetHeight() {
        int expectedHeight = 20;
        int actualHeight = this.lanternaGUI.getHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    void testgetWidth() {
        int expectedWidth = 20;
        int actualWidth = this.lanternaGUI.getWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    void testDrawText(){
        lanternaGUI.drawText(new Position(5, 10), "Hello", "#ff0000", "#ffffff");
        verify(mockTextGraphics, times(1)).putString(5, 10, "Hello");
    }

    @Test
    void testDrawRectangle(){
        lanternaGUI.drawRectangle(new Position(2, 3), 5, 4, "#00ff00");
        verify(mockTextGraphics, times(1)).fillRectangle(any(com.googlecode.lanterna.TerminalPosition.class), any(TerminalSize.class), eq(' '));
    }

    @Test
    void testChangeBackgroundColor(){
        String newColor = "#ff0000";
        lanternaGUI.changeBackgroundColor(newColor);
        assertEquals(newColor, lanternaGUI.getBackgroundColor());
    }

    @Test
    void testChangeBackgroundColorMultipleTimes(){
        lanternaGUI.changeBackgroundColor("#ff0000");
        assertEquals("#ff0000", lanternaGUI.getBackgroundColor());

        lanternaGUI.changeBackgroundColor("#00ff00");
        assertEquals("#00ff00", lanternaGUI.getBackgroundColor());

        lanternaGUI.changeBackgroundColor("#0000ff");
        assertEquals("#0000ff", lanternaGUI.getBackgroundColor());
    }

    @Test
    void testPaint(){
        lanternaGUI.paint();
        verify(mockTextGraphics, times(1)).setBackgroundColor(any());
        verify(mockTextGraphics, times(1)).fill(' ');
    }

    @Test
    void testClear(){
        lanternaGUI.clear();
        verify(mockScreen, times(1)).clear();
    }

    @Test
    void testRefresh() throws IOException {
        lanternaGUI.refresh();
        verify(mockScreen, times(1)).refresh();
    }

    @Test
    void testClose() throws IOException {
        lanternaGUI.close();
        verify(mockScreen, times(1)).close();
    }

    @Test
    void testDrawFloor() {
        Position pos = new Position(5, 10);
        lanternaGUI.drawFloor(pos);

        verify(mockTextGraphics, times(1)).setBackgroundColor(any());
        verify(mockTextGraphics, times(1)).setForegroundColor(any());
        verify(mockTextGraphics, times(1)).putString(5, 10, " ");
        assertEquals("#c4a484", lanternaGUI.getBackgroundColor());
    }

    @Test
    void testDrawChef1() {
        Position pos = new Position(3, 7);
        lanternaGUI.drawChef1(pos);
        verify(mockTextGraphics, times(1)).putString(3, 7, "C");
    }

    @Test
    void testDrawChef2() {
        Position pos = new Position(8, 12);
        lanternaGUI.drawChef2(pos);
        verify(mockTextGraphics, times(1)).putString(8, 12, "C");
    }

    @Test
    void testDrawTextBox() {
        Position pos = new Position(2, 2);
        String text = "Hello";
        int padding = 2;

        lanternaGUI.drawTextBox(pos, text, "#000000", "#ffffff", padding);

        verify(mockTextGraphics, times(1)).fillRectangle(
                any(TerminalPosition.class),
                any(TerminalSize.class),
                eq(' ')
        );
        verify(mockTextGraphics, times(1)).putString(4, 4, text);
    }

    @Test
    void testDrawCounter() {
        Position pos = new Position(10, 10);
        this.lanternaGUI.drawCounter(pos);
        verify(mockTextGraphics, times(1)).putString(10, 10, "#");
    }

    @Test
    void testDrawRawBeef() {
        Position pos = new Position(5, 5);
        this.lanternaGUI.drawRawBeef(pos);
        verify(mockTextGraphics, times(1)).putString(5, 5, "B");
    }

    @Test
    void testDrawGrilledBeef() {
        Position pos = new Position(5, 5);
        this.lanternaGUI.drawGrilledBeef(pos);
        verify(mockTextGraphics, times(1)).putString(5, 5, "B");
    }

    @Test
    void testDrawGrill() {
        Position pos = new Position(15, 15);
        this.lanternaGUI.drawGrill(pos);
        verify(mockTextGraphics, times(1)).putString(15, 15, "G");
    }

    @Test
    void testDrawFryer() {
        Position pos = new Position(12, 8);
        this.lanternaGUI.drawFryer(pos);
        verify(mockTextGraphics, times(1)).putString(12, 8, "F");
    }

    @Test
    void testDrawPotatoSoup() {
        Position pos = new Position(6, 9);
        this.lanternaGUI.drawPotatoSoup(pos);
        verify(mockTextGraphics, times(1)).putString(6, 9, "*");
    }

    @Test
    void testDrawSalad() {
        Position pos = new Position(11, 13);
        this.lanternaGUI.drawSalad(pos);
        verify(mockTextGraphics, times(1)).putString(11, 13, "S");
    }

}