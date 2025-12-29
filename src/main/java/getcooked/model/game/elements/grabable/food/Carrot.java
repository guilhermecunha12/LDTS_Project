package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.CarrotViewer;
import getcooked.viewer.game.ElementViewer;

public class Carrot extends Food {

    public Carrot(int x, int y){
        super(x,y);
    }

    @Override
    public DishType getCookedDishType() {
        if(this.state == FoodState.CHOPPED) {
            return DishType.CARROT_SOUP;
        }
        return null;
    }

    @Override
    public ElementViewer<Carrot> getViewer() {
        return new CarrotViewer();
    }
}
