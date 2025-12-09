package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;

public class Counter extends Interactable{

    private Grabable item;

    public Counter(int x,int y){
        super(x,y);
    }

    public Grabable getItem() {
        return item;
    }

    public void setItem(Grabable item) {
        this.item = item;
    }

    private boolean isEmpty() {
        return this.item == null;
    }

    @Override
    public void interact(Chef chef) {
        if (!chef.isHoldingSomething() && !this.isEmpty()){
            chef.pickUp(item);
            setItem(null);
        }
        else if (chef.isHoldingSomething() && this.isEmpty()){
            Grabable grabable = chef.drop();
            grabable.placeAt(getPosition());
            setItem(grabable);
        }
    }
}
