package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;

public abstract class Food extends Grabable {

    protected FoodState state;

    protected Food(int x, int y){
        super(x,y);
        this.state = FoodState.RAW;
    }

    public FoodState getState(){
        return state;
    }

    public void setState(FoodState foodState) {
        this.state = foodState;
    }

    public abstract DishType getCookedDishType();

    @Override
    public void interactWithKitchenware(Kitchenware kitchenware, Chef chef) {
        kitchenware.acceptFood(this, chef);
    }
}
