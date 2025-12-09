package getcooked.model.game.elements.interactable;

import getcooked.model.game.Kitchen;
import getcooked.model.game.Order;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;

import java.util.List;

public class DeliverySpot extends Interactable {

    private Kitchen kitchen;

    public DeliverySpot(int x, int y) {
        super(x,y);
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public void interact(Chef chef, Kitchen kitchen) {
        if (this.kitchen != kitchen) this.kitchen = kitchen;
        interact(chef);
    }

    @Override
    public void interact(Chef chef) {
        if(chef.isHoldingSomething() && chef.getItem() instanceof Plate) {
            Plate plate = (Plate) chef.getItem();

            if (plate.hasContent()) {
                Dish dish = plate.getContent();
                List<Order> activeOrders = kitchen.getActiveOrders();

                for (Order order : activeOrders) {
                    Dish orderDish = order.getDesiredDish();

                    if (dish.equals(orderDish)) {
                        chef.drop();
                        kitchen.removeActiveOrder(order);
                        kitchen.incrementOrdersDelivered();
                        return; // only one order is satisfied
                    }
                }
            }
        }
    }
}
