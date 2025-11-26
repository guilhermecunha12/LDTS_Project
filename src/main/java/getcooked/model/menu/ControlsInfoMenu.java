package getcooked.model.menu;

import java.util.Arrays;

public class ControlsInfoMenu extends Menu{

    private final boolean onePlayer;

    public ControlsInfoMenu(boolean is1Player) {
        super(Arrays.asList("W", "A", "S", "D", "Space", "W - move up", "A - move left", "S - move down", "D - move right", "Space - grab/drop",
                "U", "L", "D", "R", "ENTER", "UP - move up", "DOWN - move down", "LEFT - move left", "RIGHT - move right", "ENTER - grab/drop",
                "ESC - Escape Menu"));


        this.onePlayer = is1Player;
    }

    public boolean isOnePlayer() {
        return onePlayer;
    }
}
