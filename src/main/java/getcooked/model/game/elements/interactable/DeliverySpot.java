package getcooked.model.game.elements.interactable;

import getcooked.model.game.Kitchen;
import getcooked.model.game.Order;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;

import java.util.List;

public class DeliverySpot extends Interactable {

    private Kitchen kitchen;

    public DeliverySpot(int x, int y) {
        super(x,y);
    }

    public void interact(Chef chef,Kitchen kitchen) {
        if(this.kitchen == null) this.kitchen = kitchen;
        interact(chef);
    }

    @Override
    public void interact(Chef chef) {
        if(chef.isHoldingSomething() && chef.getItem() instanceof Plate) {
            Plate plate = (Plate) chef.getItem();
            List<Order> orders = kitchen.getActiveOrders();
            for(Order order : orders){
                if(plate.hasContent() && plate.getContent().getType() == order.getDesiredDish().getType()){
                    chef.drop();
                    kitchen.removeActiveOrder(order);
                    return;
                }
            }
        }
    }
}
