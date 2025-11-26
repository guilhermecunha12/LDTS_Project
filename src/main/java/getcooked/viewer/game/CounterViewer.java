package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.elements.interactable.Counter;

public class CounterViewer implements ElementViewer<Counter>{

    @Override
    public void draw(Counter counter, GUI gui) {
        gui.drawCounter(counter.getPosition());
    }
}
