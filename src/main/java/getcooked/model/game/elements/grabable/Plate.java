
package getcooked.model.game.elements.grabable;

import getcooked.model.game.dish.Dish;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.PlateViewer;

public class Plate extends Grabable {

    private Dish content;

    public Plate(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer<Plate> getViewer() {
        return new PlateViewer();
    }

    public boolean hasContent(){
        return content != null;
    }

    public Dish getContent(){
        return content;
    }

    public void setContent(Dish dish) {
        this.content = dish;
    }
}
