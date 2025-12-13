package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

//TODO: review if ChefViewers could be unified
public class Chef1Viewer implements ElementViewer<Chef>{
    private final Sprite sprite;
    public Chef1Viewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }
    /*
    @Override
    public void draw(Chef chef, GUI gui) {
        gui.drawChef1(chef.getPosition());
        Grabable item = chef.getItem();
        if(item == null) return;

        else if (item instanceof Potato potato && potato.getState() == FoodState.RAW) gui.drawPotato(chef.getPosition());
        else if (item instanceof Potato potato && potato.getState() == FoodState.CHOPPED) gui.drawChoppedPotato(chef.getPosition());
        else if (item instanceof Carrot carrot && carrot.getState() == FoodState.RAW) gui.drawCarrot(chef.getPosition());
        else if (item instanceof Carrot carrot && carrot.getState() == FoodState.CHOPPED) gui.drawChoppedCarrot(chef.getPosition());

        else if(item instanceof Plate plate){
            gui.drawPlate(chef.getPosition());
            Dish dish = plate.getContent();
            if(dish != null){
                switch (dish.getType()) {
                    case CARROT_SOUP:
                        gui.drawCarrotSoup(chef.getPosition());
                        break;
                    case POTATO_SOUP:
                        gui.drawPotatoSoup(chef.getPosition());
                        break;
                    default:
                        break;
                }
            }
        }
    }*/
    @Override
    public void draw(Chef chef, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = chef.getPosition().getX() * SCALE;
        int pixelY = chef.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
