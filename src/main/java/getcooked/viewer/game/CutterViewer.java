package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;

public class CutterViewer implements ElementViewer<CounterCutter> {
    @Override
    public void draw(CounterCutter element, GUI gui) {
        gui.drawCutter(element.getPosition());
        Food content = element.getContent();
        if (element.isFinished()){
            if (content instanceof Potato) gui.drawChoppedPotato(element.getPosition());
            else if (content instanceof Carrot) gui.drawChoppedCarrot(element.getPosition());
        }
    }
}
