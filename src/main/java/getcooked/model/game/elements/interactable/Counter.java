package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;

public class Counter extends Interactable{

    private Grabable content;

    public Counter(int x,int y){
        super(x,y);
    }

    public Grabable getContent() {
        return content;
    }

    public void setContent(Grabable content) {
        this.content = content;
    }

    private boolean isEmpty() {
        return this.content == null;
    }

    @Override
    public void interact(Chef chef) {
        if (!chef.isHoldingSomething() && !this.isEmpty()){
            chef.pickUp(content);
            this.setContent(null);
        }
        else if (chef.isHoldingSomething() && this.isEmpty()){
            Grabable grabable = chef.drop();
            grabable.placeAt(this.getPosition());
            this.setContent(grabable);
        }
    }
}
