package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Soda;

public class SodaDispenser extends Dispenser {

    public SodaDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Soda(x, y);
    }

    //TODO: rever a lógica de entregar sodas (não são dishes, pq o chef não pode pegar em dishes
}
