package getcooked.model.game.kitchen.elements.grabable.food;

public class Carrot extends Food {

    public Carrot(int x, int y){
        super(x,y);
    }

    @Override
    public boolean chop(){
        if(state == FoodState.RAW){
            state = FoodState.COOKED;
            return true;
        }
        return false;
    }
}
