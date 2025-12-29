package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.SodaViewer;

public class Soda extends Food {

    public Soda(int x, int y) {
        super(x, y);
    }

    @Override
    public DishType getCookedDishType() {
        return DishType.SODA;
    }

    @Override
    public ElementViewer<Soda> getViewer() {
        return new SodaViewer();
    }
}
