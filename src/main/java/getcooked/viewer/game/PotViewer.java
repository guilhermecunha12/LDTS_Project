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
        Food content = pot.getContent();
        if (pot.isFinished()){
            if (content instanceof Potato) gui.drawPotatoSoup(pot.getPosition());
            else if (content instanceof Carrot) gui.drawCarrotSoup(pot.getPosition());
        }
    }
}
