package getcooked.model.game.elements.interactable.kitchenware.cutter;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Kebab;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.InputBehaviour;

public class KebabCutter extends Cutter {

    public KebabCutter(int x, int y) {
        super(x, y, new InputBehaviour(5));
    }

    @Override
    public void finish() {
        content = new Kebab(this.getX(), this.getY());
        super.finish();
    }

    @Override // KebabCutter requires that the chef has a plate in hand
    public void interact(Chef chef) {
        if (isFinished()) {
            if (chef.isHoldingSomething() && chef.getItem() instanceof Plate plate && !plate.hasContent()) {
                plate.setContent(new Dish(DishType.KEBAB));
                clear();
            }
        } else {
            if (!chef.isHoldingSomething()) {
                processFood();
            }
        }
    }
}
