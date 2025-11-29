package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.elements.grabable.Grabable;

public abstract class Food extends Grabable {

    public enum FoodState {RAW, CHOPPED, COOKED, FRIED}

    protected FoodState state;

    public Food(int x, int y){
        super(x,y);
        this.state = FoodState.RAW;
    }

    public FoodState getState(){
        return state;
    }

    public boolean chop() { return false;}
    public boolean cook() { return false;}
}
