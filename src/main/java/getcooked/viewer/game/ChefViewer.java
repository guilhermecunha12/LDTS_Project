package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import static getcooked.viewer.Sprite.SCALE;

public class ChefViewer implements ElementViewer<Chef>{
    private final int index;

    public ChefViewer(int index) {
        this.index = index;
    }

    @Override
    public void draw(Chef chef, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = chef.getPosition().getX() * SCALE;
        int pixelY = chef.getPosition().getY() * SCALE;
        Sprite sprite = switch (chef.getDirection()){
            case UP -> new Sprite("sprites/chef" + index + "_back.png");
            case DOWN -> new Sprite("sprites/chef" + index + "_front.png");
            case LEFT -> new Sprite("sprites/chef" + index + "_left.png");
            case RIGHT -> new Sprite("sprites/chef" + index + "_right.png");
        };

        sprite.draw(gui, pixelX, pixelY);
        Grabable grabable = chef.getItem();
        if(grabable == null) return;
        else if(grabable instanceof Potato potato) {
            PotatoViewer potatoViewer = new PotatoViewer(potato);
            potatoViewer.draw(potato, gui);
        }
        else if (grabable instanceof Carrot carrot){
            CarrotViewer carrotViewer = new CarrotViewer(carrot);
            carrotViewer.draw(carrot, gui);
        }
        else if(grabable instanceof Plate plate){
            PlateViewer plateViewer = new PlateViewer(plate);
            plateViewer.draw(plate, gui);
        }
    }
}