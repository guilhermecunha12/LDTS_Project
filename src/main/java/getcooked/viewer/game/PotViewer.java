package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.cookware.Pot;

import java.util.List;

public class PotViewer implements ElementViewer<Pot>{

    @Override
    public void draw(Pot pot, GUI gui) {
        gui.drawPot(pot.getPosition());
        List<Food> contents = pot.getContents();
        if (pot.isCooked()){ // TODO: review thsi logic... pot should only have one item
            if (contents.getFirst() instanceof Potato) gui.drawPotatoSoup(pot.getPosition());
            else if (contents.getFirst() instanceof Carrot) gui.drawCarrotSoup(pot.getPosition());
        }
    }
}
