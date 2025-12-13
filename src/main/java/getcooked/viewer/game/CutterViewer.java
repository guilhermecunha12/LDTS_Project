package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.viewer.Sprite;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CutterViewer implements ElementViewer<CounterCutter> {

    private final Sprite sprite;
    public  CutterViewer(String filepath) throws Exception {
        this.sprite = new Sprite(filepath);
    }
    @Override
    public void draw(CounterCutter model, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = model.getPosition().getX() * 5;
        int pixelY = model.getPosition().getY() * 5;
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

