package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Kebab;

public class KebabDispenser extends Dispenser {

    public KebabDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Kebab(x, y);
    }

    @Override // KebabDispenser requires that the chef has a plate in hand
    public void interact(Chef chef) {
        if (chef.isHoldingSomething() && chef.getItem() instanceof Plate plate && !plate.hasContent()){
            plate.setContent(new Dish(DishType.KEBAB));
        }
    }
}
