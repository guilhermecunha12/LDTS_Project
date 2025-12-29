package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ChefViewer implements ElementViewer<Chef>{
    private final int index;

    public ChefViewer(int index) {
        this.index = index;
    }

    @Override
    public void draw(Chef chef, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        String path = "sprites/chef";
        Sprite sprite = switch (chef.getDirection()){
            case UP -> new Sprite(path + index + "_back.png");
            case DOWN -> new Sprite(path + index + "_front.png");
            case LEFT -> new Sprite(path + index + "_left.png");
            case RIGHT -> new Sprite(path + index + "_right.png");
        };
        sprite.draw(gui, chef.getX(), chef.getY(), 0, 0);
        if(chef.isHoldingSomething()){
            Grabable grabable = chef.getItem();
            grabable.getViewer().draw(grabable, gui);
        }

    }
}
