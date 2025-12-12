package getcooked.model.game.elements.interactable;

import getcooked.model.game.Order;
import getcooked.model.game.OrderManager;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;

import java.util.List;

public class DeliverySpot extends Interactable {

    private OrderManager orderManager;

    public DeliverySpot(int x, int y) {
        super(x,y);
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

        if (chef.isHoldingSomething() && chef.getItem() instanceof Plate plate && plate.hasContent()) {
            Dish dish = plate.getContent();
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
