package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.viewer.game.CounterViewer;
import getcooked.viewer.game.ElementViewer;


public class Counter extends Interactable {

    private Grabable content;
    private final char type;

    public Counter(int x, int y, char type) {
        super(x,y);
        this.type = type;
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

        } else if (chef.isHoldingSomething() && this.isEmpty()) {
            Grabable grabable = chef.drop();
            this.setContent(grabable);
        }
    }

    public char getType() {
        return this.type;
    }

    @Override
    public ElementViewer<Counter> getViewer() {
        return new CounterViewer();
    }
}
