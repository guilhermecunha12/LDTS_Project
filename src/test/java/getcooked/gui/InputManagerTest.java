package getcooked.gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InputManagerTest {

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

    private InputManager inputManager;
    private Set<Integer> activeInputs;
    private Set<Integer> previousInputs;
    private Map<Integer, Long> lastActionTime;


    @BeforeEach
    void setUp() {
        this.activeInputs = new HashSet<>();
        this.previousInputs = new HashSet<>();
        this.lastActionTime = new HashMap<>();
    }

    @Test
    void testTryProcessKeyKeyCodeJustPressed() {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        long currentTime = 1L;
        inputManager = new InputManager(activeInputs, previousInputs, lastActionTime);
        inputManager.tryProcessKey(keyCode, expectedAction, currentTime , actions);
        assertTrue(actions.contains(expectedAction));
        assertEquals(currentTime, lastActionTime.get(keyCode));
    }


    @Test
    void testTryProcessKeyKeyCodeAlreadyPressedLastActionNull() {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        previousInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        inputManager = new InputManager(activeInputs, previousInputs, lastActionTime);
        inputManager.tryProcessKey(keyCode, expectedAction, System.currentTimeMillis(), actions);
        assertFalse(actions.contains(expectedAction));
    }

    @Test
    void testTryProcessKeyKeyCodeAlreadyPressedLastActionNotNullValidThreshold()  {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        previousInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        // Bigger time than the MIN_HOLD_TIME
        long pastTime = System.currentTimeMillis() - 500; // 500 ms ago
        lastActionTime.put(keyCode, pastTime);
        Long currentTime = System.currentTimeMillis();
        inputManager = new InputManager(activeInputs, previousInputs, lastActionTime);
        inputManager.tryProcessKey(keyCode, expectedAction, currentTime, actions);
        assertTrue(actions.contains(expectedAction));
        assertEquals(currentTime, lastActionTime.get(keyCode));
    }

    @Test
    void testTryProcessKeyKeyCodeAlreadyPressedLastActionNotNullInvalidThreshold()  {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        previousInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        long currentTime = System.currentTimeMillis();
        // Smaller time than the MIN_HOLD_TIME
        Long pastTime = System.currentTimeMillis() - 30;
        lastActionTime.put(keyCode, pastTime);
        inputManager = new InputManager(activeInputs, previousInputs, lastActionTime);
        inputManager.tryProcessKey(keyCode, expectedAction, currentTime, actions);
        assertFalse(actions.contains(expectedAction));
    }

    @Test
    void testTryProcessKeyKeyCodeNotPressed() {
        Set<GUI.ACTION> actions = new HashSet<>();
        int keyCode = KeyEvent.VK_W;
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        inputManager = new InputManager(activeInputs, previousInputs, lastActionTime);
        inputManager.tryProcessKey(keyCode, expectedAction, System.currentTimeMillis(), actions);
        assertFalse(actions.contains(expectedAction));
    }

    @Test
    void testGetNextActionValidAction()  {
        int keyCode = KeyEvent.VK_W;
        activeInputs.add(keyCode);
        GUI.ACTION expectedAction = KEY_MAPPINGS.get(keyCode);
        inputManager = new InputManager(activeInputs, previousInputs, lastActionTime);
        Set<GUI.ACTION> actions = inputManager.getNextAction();
        assertTrue(actions.contains(expectedAction));
    }
}