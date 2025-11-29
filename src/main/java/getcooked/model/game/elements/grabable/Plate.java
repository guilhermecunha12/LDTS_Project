
package getcooked.model.game.elements.grabable;

import getcooked.model.game.dish.Dish;

public class Plate extends Grabable {

    private Dish content;

    public Plate(int x, int y) {
        super(x, y);
    }

    public boolean hasContent(){
        return content != null;
    }

    public Dish getContent(){
        return content;
    }

    public boolean put(Dish g) {
        if (g == null) return false;
        if (content != null) return false;
        content = g;
        return true;
    }
}
