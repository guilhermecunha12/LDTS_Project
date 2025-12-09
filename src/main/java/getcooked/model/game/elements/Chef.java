package getcooked.model.game.elements;

import getcooked.model.Position;
import getcooked.model.game.elements.grabable.Grabable;

public class Chef extends Element{

    private Grabable item;

    public Chef(int x, int y) {
        super(x, y);
        item = null;
    }

    public Grabable getItem() { return item; }

    //boolean in order to later change states of cookware
    public boolean pickUp(Grabable item) {
        if (!isHoldingSomething()){
            item.setHolder(this);
            this.item = item;
            return true;
        }
        return false;
    }

    public Grabable drop(){
        if (isHoldingSomething()){
            Grabable tmp = item;
            tmp.setHolder(null);
            this.item = null;
            return tmp;
        }
        return null;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        if (this.isHoldingSomething()) item.setPosition(position); // also update the item's position
    }

    public boolean isHoldingSomething(){
        return item != null;
    }
}
