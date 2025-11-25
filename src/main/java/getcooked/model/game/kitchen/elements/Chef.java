package getcooked.model.game.kitchen.elements;

public class Chef extends Element{

    private Grabable item;

    public Chef(int x, int y) {
        super(x, y);
        item = null;
    }

    public Grabable getItem(){
        return item;
    }

    //boolean in order to later change states of cookware
    public boolean pickUp(Grabbable item) {
        if (this.item != null) return false;
        this.item = item;
        return true;
    }

    public Grabable drop(){
        Grabable temp = item;
        this.item = null;
        return temp;
    }

    public boolean isHoldingSomething(){
        return item != null;
    }
}
