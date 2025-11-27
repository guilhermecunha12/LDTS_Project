package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.grabable.Grabable;
import getcooked.model.game.kitchen.elements.grabable.food.Food;

public class Cutter extends Interactable {
    public Cutter(int x, int y) {
        super(x, y);
    }

    @Override
    public void interact(Chef chef) {
        if (chef.isHoldingSomething()) {
            Grabable held = chef.getItem();
            if (held instanceof Food) {
                Food food = (Food) held;
                food.chop();
            }
        }
    }
}
