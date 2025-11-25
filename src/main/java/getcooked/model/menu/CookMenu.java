package getcooked.model.menu;

import java.util.Arrays;

public class CookMenu extends Menu {
    public CookMenu() {
        super(Arrays.asList("1-Player", "2-Player", "Back"));
    }
    public boolean isSelected1Player() {return isSelected(0);}
    public boolean isSelected2Player() {return isSelected(1);}
    public boolean isSelectedBack() {return isSelected(2);}
}
