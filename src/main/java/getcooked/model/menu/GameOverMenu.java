package getcooked.model.menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {

    private final int ordersDelivered;

    public GameOverMenu(int ordersDelivered) {
        super(Arrays.asList("Back to Main Menu","Exit Game"));
        this.ordersDelivered = ordersDelivered;
    }
    public int getOrdersDelivered() {
        return ordersDelivered;
    }
    public boolean isSelectedBackToMainMenu() {return isSelected(0);}
    public boolean isSelectedExitGame() {return isSelected(1);}

}
