package getcooked.model.game.elements.interactable;

import getcooked.model.game.Order;
import getcooked.model.game.OrderManager;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Salad;
import getcooked.model.game.elements.grabable.food.Soda;
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
        if (chef.isHoldingSomething()) {
            Dish dish;
            switch (chef.getItem()) {
                case Plate plate when plate.hasContent() -> dish = plate.getContent();
                case Soda ignored -> dish = new Dish(DishType.SODA);
                case Salad ignored -> dish = new Dish(DishType.SALAD);
                case null, default -> {
                    return; // when chef is holding something else
                }
            }

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
