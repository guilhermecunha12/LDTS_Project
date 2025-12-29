package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CounterCutterViewer implements ElementViewer<CounterCutter> {
    @Override
    public void draw(CounterCutter counterCutter, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/cutter.png");
        sprite.draw(gui, counterCutter.getX(), counterCutter.getY(), 0, 0);

        if (!counterCutter.isEmpty()) {
            Grabable item = counterCutter.getContent();
            item.getViewer().draw(item, gui);
        }
    }
}
