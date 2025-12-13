package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.CarrotDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;


public class CarrotDispenserViewer implements ElementViewer<CarrotDispenser> {
    private final Sprite sprite;
    public CarrotDispenserViewer(String filepath) throws Exception {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(CarrotDispenser model, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = model.getPosition().getX() * SCALE;
        int pixelY = model.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
