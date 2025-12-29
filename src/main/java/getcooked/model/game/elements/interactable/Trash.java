package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.TrashViewer;


public class Trash extends Interactable{

    public Trash(int x, int y){
        super(x,y);
    }

    @Override
    public ElementViewer<Trash> getViewer() {
        return new TrashViewer();
    }

    @Override
    public void interact(Chef chef) {
        if(chef.isHoldingSomething())
            chef.drop();
    }
}
