package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.PotatoViewer;

public class Potato extends Food {

    public Potato(int x, int y){
        super(x, y);
    }

    @Override
    public DishType getCookedDishType() {
        if(this.state == FoodState.CHOPPED) {
            return DishType.POTATO_SOUP;
        }
        return null;
    }

    @Override
    public ElementViewer<Potato> getViewer() {
        return new PotatoViewer();
    }
}
