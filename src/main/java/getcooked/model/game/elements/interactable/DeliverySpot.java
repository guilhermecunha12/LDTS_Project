package getcooked.model.game.elements.interactable;

import getcooked.gui.GUI;
import getcooked.model.game.Order;
import getcooked.model.game.OrderManager;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Salad;
import getcooked.model.game.elements.grabable.food.Soda;
import getcooked.viewer.Sprite;
import getcooked.viewer.game.DeliverySpotViewer;
import getcooked.viewer.game.ElementViewer;

import java.awt.*;
import java.util.List;

import static getcooked.viewer.Sprite.SCALE;

public class DeliverySpot extends Interactable {

    private OrderManager orderManager;

    public DeliverySpot(int x, int y) {
        super(x,y);
    }

    @Override
    public ElementViewer<DeliverySpot> getViewer() {
        return new DeliverySpotViewer();
    }

    // set only if not defined
    public void setOrderManager(OrderManager orderManager) {
        if (this.orderManager == null)
            this.orderManager = orderManager;
    }

    @Override
    public void interact(Chef chef) {
        if (this.orderManager == null)
            return;

        if (chef.isHoldingSomething()) {
            Dish dish;
            if (chef.getItem() instanceof Plate plate && plate.hasContent())
                dish = plate.getContent();
            else if (chef.getItem() instanceof Soda)
                dish = new Dish(DishType.SODA);
            else if (chef.getItem() instanceof Salad)
                dish = new Dish(DishType.SALAD);
            else return;

            List<Order> activeOrders = orderManager.getActiveOrders();

            for (Order order : activeOrders) {
                Dish orderDish = order.getDesiredDish();
                
                if (dish.equals(orderDish)) {
                    chef.drop();
                    orderManager.removeActiveOrder(order);
                    orderManager.incrementOrdersDelivered();
                    return; // only one order is satisfied
                }
            }
        }
    }
}
