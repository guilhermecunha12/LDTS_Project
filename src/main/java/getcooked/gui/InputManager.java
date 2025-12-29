package getcooked.gui;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InputManager {
    private static final long MIN_HOLD_TIME = 150;// Time needed to consider the keyHold a valid Hold
    private final Set<Integer> activeInputs; // Currently pressed keys
    private final Set<Integer> previousInputs; // Pressed keys in the previous frame
    private final Map<Integer, Long> lastActionTimes; // Last action times for keys

    public InputManager(Terminal terminal) {
        this.activeInputs = new HashSet<>();
        this.previousInputs = new HashSet<>();
        this.lastActionTimes = new HashMap<>();
        addKeyListener(terminal);
    }

    // Static key-to-action mapping for all available keybinds
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

    private void addKeyListener(Terminal terminal) {
        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                activeInputs.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                activeInputs.remove(e.getKeyCode());
                // When the key is released, wipe the last action time
                lastActionTimes.remove(e.getKeyCode());
            }
        });
    }

    public Set<GUI.ACTION> getNextAction() {
        // Start with empty set of actions
        Set<GUI.ACTION> actions = new HashSet<>();
        // Current time for the getNextAction processing

        // Processes the map iterating like a set
        for (Map.Entry<Integer, GUI.ACTION> mapping : KEY_MAPPINGS.entrySet()) {
            tryProcessKey(mapping.getKey(), mapping.getValue(), System.currentTimeMillis(), actions);
        }

        // Update previousInputs for next frame
        previousInputs.clear();
        previousInputs.addAll(activeInputs);

        return actions;
    }

    // Public for testing purposes
    public void tryProcessKey(int keyCode, GUI.ACTION action, long currentTime, Set<GUI.ACTION> actions) {
        // Key not pressed, skip processing
        if (activeInputs.contains(keyCode)) {
            // Check if this is a newly pressed key (not in previous frame)
            boolean justPressed = !previousInputs.contains(keyCode);
            if (justPressed) {
                // New key press - add action and record time
                actions.add(action);
                lastActionTimes.put(keyCode, currentTime);
            } else {
                // Key is being held - check if enough time has passed for repeat
                Long lastActionTime = lastActionTimes.get(keyCode);
                if (lastActionTime != null && currentTime - lastActionTime > MIN_HOLD_TIME) {
                    // Repeat threshold exceeded - add action and update timer
                    actions.add(action);
                    lastActionTimes.put(keyCode, currentTime);
                }
            }
        }
    }
}
