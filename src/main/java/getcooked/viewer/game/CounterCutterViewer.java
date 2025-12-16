package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CounterCutterViewer extends ElementViewer<CounterCutter> {
    @Override
    public void draw(CounterCutter counterCutter, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/cutter.png");
        sprite.draw(gui, counterCutter.getX(), counterCutter.getY(), 0);

        if (!counterCutter.isEmpty()) {
            Grabable item = counterCutter.getContent();
            item.getViewer().draw(item, gui);
        }
    }
}
