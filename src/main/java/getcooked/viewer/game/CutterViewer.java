package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CutterViewer implements ElementViewer<CounterCutter> {

    private final Sprite sprite;
    public  CutterViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }
    @Override
    public void draw(CounterCutter model, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = model.getPosition().getX() * SCALE;
        int pixelY = model.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
        /*
        gui.drawCutter(element.getPosition());
        Food content = element.getContent();
        if (element.isFinished()){
            if (content instanceof Potato) gui.drawChoppedPotato(element.getPosition());
            else if (content instanceof Carrot) gui.drawChoppedCarrot(element.getPosition());
        }
    }
    */
}

