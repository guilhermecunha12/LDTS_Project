package getcooked.model.menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {

    public GameOverMenu() {super(Arrays.asList("Back to Main Menu","Exit Game"));}
    public boolean isSelectedBackToMainMenu() {return isSelected(0);}
    public boolean isSelectedExitGame() {return isSelected(1);}

}
