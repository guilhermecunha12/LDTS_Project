package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.BeefViewer;
import getcooked.viewer.game.ElementViewer;

public class Beef extends Food {

    public Beef(int x, int y) {
        super(x, y);
    }

    @Override
    public DishType getCookedDishType() {
        if(this.state == FoodState.RAW) {
            return DishType.STEAK;
        }
        return null;
    }

    @Override
    public ElementViewer<Beef> getViewer() {
        return new BeefViewer();
    }
}
