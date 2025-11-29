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

    public Grabable setItem(Grabable item) {
        return this.item = item;
    }

    @Override
    public void interact(Chef chef) {
        //pegar da counter
        if(!chef.isHoldingSomething() && item != null){
            chef.pickUp(item);
            this.setItem(null);
        }
        else if(chef.isHoldingSomething() && item == null){
            Grabable tmp = chef.drop();
            //Passar a posição deste counter
            tmp.placeAt(getPosition());
            this.setItem(tmp);
        }
    }
}
