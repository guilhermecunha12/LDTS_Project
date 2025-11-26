package getcooked.model.menu;

import java.util.List;

public class ControlsInfoMenu extends Menu{

    private final boolean onePlayer;

    public ControlsInfoMenu(boolean is1Player) {
        super(List.of());
        this.onePlayer = is1Player;
    }

    public boolean isOnePlayer() {
        return onePlayer;
    }
}
