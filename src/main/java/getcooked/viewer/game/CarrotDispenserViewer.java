package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.CarrotDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class CarrotDispenserViewer implements ElementViewer<CarrotDispenser> {


    @Override
    public void draw(CarrotDispenser element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/carrot_dispenser.png");
        sprite.draw(gui, element.getX(), element.getY(), 0, 0);
    }
}
