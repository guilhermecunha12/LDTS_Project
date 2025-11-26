package getcooked.model.game.kitchen.elements;

import getcooked.model.game.kitchen.elements.grabable.Grabable;

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

    public boolean isHoldingSomething(){
        return item != null;
    }
}
