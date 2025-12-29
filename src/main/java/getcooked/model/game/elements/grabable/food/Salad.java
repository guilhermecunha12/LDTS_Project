package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.SaladViewer;

public class Salad extends Food {

    public Salad(int x, int y) {
        super(x, y);
    }

    @Override
    public DishType getCookedDishType() {
        return DishType.SALAD;
    }

    @Override
    public ElementViewer<Salad> getViewer() {
        return new SaladViewer();
    }
}
