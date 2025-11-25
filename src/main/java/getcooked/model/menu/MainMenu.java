package getcooked.model.menu;

import java.util.Arrays;

public class MainMenu extends Menu {
    public MainMenu() {
        super(Arrays.asList("Cook","Settings","Exit"));
    }

    public boolean isSelectedCook() { return isSelected(0); }
    public boolean isSelectedSettings() { return isSelected(1); }
    public boolean isSelectedExit()  { return isSelected(2); }
}
