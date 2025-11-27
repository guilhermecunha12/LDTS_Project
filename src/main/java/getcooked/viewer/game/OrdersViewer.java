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
    protected void drawElements(GUI gui) {
        int h = gui.getHeight();
        int w = gui.getWidth();
        String orderColor = "ffff88";
        for (int i = 0; i < getModel().size(); i++) { // iterate through the current active orders
            Position curPos = new Position(1 + 6*i, h - 5);
            gui.drawRectangle(curPos, 5, 4, orderColor);
            gui.drawText(curPos, getModel().get(i).getDesiredDish().toString(),
                    "#000000", orderColor);
            gui.drawText(new Position(curPos.getX(), curPos.getY() + 3),
                    String.valueOf(getModel().get(i).getRemainingTime()), "#000000", orderColor);
        }
    }
}
