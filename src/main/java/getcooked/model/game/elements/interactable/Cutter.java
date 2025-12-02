package getcooked.model.game.elements.interactable;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.cookware.Cookware;

import java.util.HashSet;

public class Cutter extends Cookware {
    public Cutter(int x, int y) {
        super(x, y);
        this.allowedFoods = new HashSet<>();
        this.allowedFoods.add(Potato.class);
        this.allowedFoods.add(Carrot.class);
    }

    public void chop(){
        if(content.getState() ==  FoodState.RAW){
            content.setState(FoodState.CHOPPED);
        }
    }

    @Override
    public void interact(Chef chef) {
        if (chef.isHoldingSomething()){
            Grabable held = chef.getItem();
            if (held instanceof Food food) {
                if (canAdd(food)) {
                    chef.drop();
                    addContent(food);
                    processFood();
                }
            }
        }
        else{
            if (isFinished()){
                Food finishedFood = content;
                chef.pickUp(finishedFood);
                clearCookware();
            }
        }
    }


    @Override
    public void processFood() {
        chop();
        finished = true;
    }

    @Override
    protected boolean accepts(Food food) {
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.RAW;
    }

    @Override
    protected Dish createFinishedDish() {
        return null;
    }
}
