package getcooked.model.game.elements.interactable;

import getcooked.model.game.Order;
import getcooked.model.game.OrderManager;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;
import getcooked.viewer.game.DeliverySpotViewer;
import getcooked.viewer.game.ElementViewer;

import java.util.List;


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
            Dish dish = chef.getItem().toDeliver();
            if (dish == null) return;

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
