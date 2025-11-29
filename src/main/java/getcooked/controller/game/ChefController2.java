package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.model.game.elements.interactable.Trash;
import getcooked.model.game.elements.interactable.cookware.Cookware;
import getcooked.model.game.elements.interactable.dispenser.Dispenser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChefController2 extends Controller<Kitchen> {
    private final Chef chef;

    public ChefController2(Chef chef, Kitchen kitchen) {
        super(kitchen);
        this.chef = chef;
    }

    public void moveChefLeft(){
        moveChef(getModel().getChefs().get(1).getPosition().getLeft());
    }

    public void moveChefUp(){
        moveChef(getModel().getChefs().get(1).getPosition().getUp());
    }

    public void moveChefDown(){
        moveChef(getModel().getChefs().get(1).getPosition().getDown());
    }

    public void moveChefRight(){
        moveChef(getModel().getChefs().get(1).getPosition().getRight());
    }

    public void moveChef(Position position){
        if(getModel().isEmpty(position)){
            chef.setPosition(position);
        }
    }

    public void ChefSelect(){
        // Define a Order, so that first comes objects bellow -> left -> up -> right
        Position position_up = chef.getPosition().getUp();
        Position position_down = chef.getPosition().getDown();
        Position position_left = chef.getPosition().getLeft();
        Position position_right = chef.getPosition().getRight();
        List<Position> positions = new ArrayList<>();
        positions = List.of(position_down, position_left, position_up, position_right);
        for(Position position : positions){
            if(getModel().getTile(position) instanceof Counter) {
                ((Counter) getModel().getTile(position)).interact(chef);
                return;
            }

            else if(getModel().getTile(position) instanceof Trash){
                ((Trash) getModel().getTile(position)).interact(chef);
                return;
            }

            else if(getModel().getTile(position) instanceof Dispenser){
                ((Dispenser) getModel().getTile(position)).interact(chef);
                return;
            }

            else if (getModel().getTile(position) instanceof Cookware) {
                ((Cookware) getModel().getTile(position)).interact(chef);
                return;
            }

            else if (getModel().getTile(position) instanceof DeliverySpot){
                ((DeliverySpot) getModel().getTile(position)).interact(chef,getModel());
                return;
            }
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        if (actions.contains(GUI.ACTION.PLAYER2_UP)) {
            moveChefUp();
        }
        if (actions.contains(GUI.ACTION.PLAYER2_DOWN)) {
            moveChefDown();
        }
        if (actions.contains(GUI.ACTION.PLAYER2_LEFT)) {
            moveChefLeft();
        }
        if (actions.contains(GUI.ACTION.PLAYER2_RIGHT)) {
            moveChefRight();
        }
        if (actions.contains(GUI.ACTION.PLAYER2_SELECT)) {
            ChefSelect();
        }
    }
}
