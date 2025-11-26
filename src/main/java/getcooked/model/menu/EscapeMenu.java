package getcooked.model.menu;

import java.util.Arrays;

public class EscapeMenu extends Menu {
    //TODO: consider adding settings (different from main menu settings because back does not go to main menu)
    public EscapeMenu() {
        super(Arrays.asList("Resume","Exit to Main Menu"));
    }

    public boolean isSelectedResume(){return isSelected(0);}
    public boolean isSelectedExitToMainMenu(){return isSelected(1);}
}
