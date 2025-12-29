package getcooked.model.game.elements.interactable.kitchenware.cutter;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.InputBehaviour;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.KebabCutterViewer;

public class KebabCutter extends Cutter {

    public KebabCutter(int x, int y) {
        super(x, y, new InputBehaviour(5));
    }

    @Override
    public ElementViewer<KebabCutter> getViewer() {
        return new KebabCutterViewer();
    }

    @Override
    public void finish() {
        content = new KebabDummy(getX(), getY());
        super.finish();
    }

    // Kebab is only used to set KebabCounter's content different from null
    private static class KebabDummy extends Food {
        protected KebabDummy(int x, int y) {
            super(x, y);
        }

        @Override
        public ElementViewer<KebabDummy> getViewer() {
            return null;
        }
    }

    @Override // KebabCutter requires that the chef has a plate in hand
    public void interact(Chef chef) {
        if (isFinished()) {
            if (chef.isHoldingSomething()) {
                chef.getItem().interactWithKitchenware(this, chef);
            }
        } else {
            if (!chef.isHoldingSomething() && isEmpty()) { // no kebab yet generated
                processFood();
            }
        }
    }

    @Override
    public void acceptPlate(Plate plate, Chef chef) {
        if (!plate.hasContent()) {
            plate.setContent(new Dish(DishType.KEBAB));
            clear();
            }
    }
}
