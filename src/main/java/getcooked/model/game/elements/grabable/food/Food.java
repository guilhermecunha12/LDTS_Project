package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.elements.grabable.Grabable;

public abstract class Food extends Grabable {

    protected FoodState state;

    public Food(int x, int y){
        super(x,y);
        this.state = FoodState.RAW;
    }

    public FoodState getState(){
        return state;
    }

    public void setState(FoodState foodState) {
        this.state = foodState;
    }
}
