package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.Order;
import getcooked.viewer.Viewer;

import java.util.List;

public class OrdersViewer extends Viewer<List<Order>> {

    public OrdersViewer(List<Order> activeOrders) {
        super(activeOrders);
    }

    @Override
    public void drawElements(GUI gui) {
        List<Order> activeOrders = getModel();
        int h = gui.getHeight();

        String orderColor = "#ffff88";
        gui.drawText(new Position(1, h - 3), "Orders: ", "#ffffff", "#000000");
        int nextX = 9; // position for the next order
        for (int i = 0; i < activeOrders.size(); i++) { // iterate through the current active orders
            String curDishType = activeOrders.get(i).getDesiredDish().getType().toString();
            int curDishTypeLength = curDishType.length();

            Position curPos = new Position(nextX, h - 5);
            nextX += curDishTypeLength + 1; // position for the next order
            gui.drawRectangle(curPos, curDishTypeLength, 4, orderColor);
            gui.drawText(curPos, curDishType, "#000000", orderColor);
            gui.drawText(new Position(curPos.getX(), curPos.getY() + 3),
                    String.valueOf(activeOrders.get(i).getRemainingTime() / 30), "#000000", orderColor);
        }
    }
}
