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
import java.util.List;
import java.util.Set;

public class ChefController extends Controller<Kitchen> {
    private final Chef chef;
    private final int index;

    public ChefController(Chef chef, Kitchen kitchen, int index) {
        super(kitchen);
        this.chef = chef;
        this.index = index;
    }

    public void moveChefLeft(){
        moveChef(getModel().getChefs().get(index).getPosition().getLeft());
    }

    public void moveChefUp(){
        moveChef(getModel().getChefs().get(index).getPosition().getUp());
    }

    public void moveChefDown(){
        moveChef(getModel().getChefs().get(index).getPosition().getDown());
    }

    public void moveChefRight(){
        moveChef(getModel().getChefs().get(index).getPosition().getRight());
    }

    public void moveChef(Position position){
        if(getModel().isEmpty(position)) chef.setPosition(position);
    }

    public void ChefSelect(){
        // Define a Order, so that first comes objects bellow -> left -> up -> right
        Position positionUp = chef.getPosition().getUp();
        Position positionDown = chef.getPosition().getDown();
        Position positionLeft = chef.getPosition().getLeft();
        Position positionRight = chef.getPosition().getRight();
        List<Position> positions = List.of(positionDown, positionLeft, positionUp, positionRight);
        for(Position position : positions){
            if(getModel().getTile(position) instanceof Counter counter) {
                counter.interact(chef);
                return;
            }

            else if(getModel().getTile(position) instanceof Trash trash){
                trash.interact(chef);
                return;
            }

            else if(getModel().getTile(position) instanceof Dispenser dispenser){
                dispenser.interact(chef);
                return;
            }

            else if (getModel().getTile(position) instanceof Cookware cookware) {
                cookware.interact(chef);
                return;
            }

            else if (getModel().getTile(position) instanceof DeliverySpot deliverySpot){
                deliverySpot.setOrderManager(getModel().getOrderManager());
                deliverySpot.interact(chef);
                return;
            }
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        if(index == 0){
            if (actions.contains(GUI.ACTION.PLAYER1_UP)) {
                moveChefUp();
            }
            if (actions.contains(GUI.ACTION.PLAYER1_DOWN)) {
                moveChefDown();
            }
            if (actions.contains(GUI.ACTION.PLAYER1_LEFT)) {
                moveChefLeft();
            }
            if (actions.contains(GUI.ACTION.PLAYER1_RIGHT)) {
                moveChefRight();
            }
            if (actions.contains(GUI.ACTION.PLAYER1_SELECT)) {
                ChefSelect();
            }
        }
        else if (index == 1) {
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
}
