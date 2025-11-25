package getcooked.model.menu;

import java.util.Arrays;

public class SettingsMenu extends Menu {
    public SettingsMenu() {
        super(Arrays.asList("Volume", "Back"));
    }
    public boolean isSelectedVolume() { return isSelected(0); }
    public boolean isSelectedBack() { return isSelected(1); }
}
