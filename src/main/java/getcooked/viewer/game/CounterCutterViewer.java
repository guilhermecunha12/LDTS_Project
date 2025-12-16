package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CounterCutterViewer implements ElementViewer<CounterCutter> {
    @Override
    public void draw(CounterCutter counterCutter, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/cutter.png");
        int pixelX = counterCutter.getPosition().getX() * SCALE;
        int pixelY = counterCutter.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);

        if (!counterCutter.isEmpty()) {
            if (counterCutter.getContent() instanceof Potato potato) {
                new PotatoViewer().draw(potato, gui);
            } else if (counterCutter.getContent() instanceof Carrot carrot) {
                new CarrotViewer().draw(carrot, gui);
            }
        }
    }
}
