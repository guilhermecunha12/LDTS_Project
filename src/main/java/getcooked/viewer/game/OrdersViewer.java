package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.Order;
import getcooked.model.game.kitchen.elements.grabable.DishType;
import getcooked.viewer.Viewer;

import java.util.List;

public class OrdersViewer extends Viewer<List<Order>> {

    public OrdersViewer(List<Order> activeOrders) {
        super(activeOrders);
    }

    @Override // TODO: scale the order box in relation with the dish string
    protected void drawElements(GUI gui) {
        int h = gui.getHeight();
        int w = gui.getWidth();
        int nextX = 1; // position for the next order
        String orderColor = "#ffff88";
        for (int i = 0; i < getModel().size(); i++) { // iterate through the current active orders
            String curDishType = getModel().get(i).getDesiredDish().getType().toString();
            int curDishTypeLength = curDishType.length();

            Position curPos = new Position(nextX, h - 5);
            nextX += curDishTypeLength + 1; // position for the next order
            gui.drawRectangle(curPos, curDishTypeLength, 4, orderColor);
            gui.drawText(curPos, curDishType, "#000000", orderColor);
            gui.drawText(new Position(curPos.getX(), curPos.getY() + 3),
                    String.valueOf(getModel().get(i).getRemainingTime() / 30), "#000000", orderColor);
        }
    }
}
