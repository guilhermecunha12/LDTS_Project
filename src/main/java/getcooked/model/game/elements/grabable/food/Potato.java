package getcooked.model.game.elements.grabable.food;

public class Potato extends Food {

    public Potato(int x, int y){
        super(x, y);
    }

    @Override
    public boolean chop(){
        if(state == FoodState.RAW){
            state = FoodState.CHOPPED;
            return true;
        }
        return false;
    }
}
