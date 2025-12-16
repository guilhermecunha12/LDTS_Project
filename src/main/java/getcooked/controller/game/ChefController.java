package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.model.game.elements.interactable.Interactable;

import java.io.IOException;
import java.util.Set;

public class ChefController extends Controller<Kitchen> {
    private final Chef chef;

    public ChefController(Chef chef, Kitchen kitchen) {
        super(kitchen);
        this.chef = chef;
    }

    public void moveChefLeft(){
        moveChef(getModel().getChefs().get(chef.getIndex()).getPosition().getLeft());
        chef.turnLeft();
    }

    public void moveChefUp(){
        moveChef(getModel().getChefs().get(chef.getIndex()).getPosition().getUp());
        chef.turnUp();
    }

    public void moveChefDown(){
        moveChef(getModel().getChefs().get(chef.getIndex()).getPosition().getDown());
        chef.turnDown();
    }

    public void moveChefRight(){
        moveChef(getModel().getChefs().get(chef.getIndex()).getPosition().getRight());
        chef.turnRight();
    }

    public void moveChef(Position position){
        if(getModel().isEmpty(position)) chef.setPosition(position);
    }

    public void chefSelect(){
        Position toInteract = chef.getFrontPosition();
        Tile tileToInteract = getModel().getTile(toInteract);

        if (tileToInteract instanceof Interactable interactable) {
            if (tileToInteract instanceof DeliverySpot deliverySpot) {
                deliverySpot.setOrderManager(getModel().getOrderManager());
            }
            interactable.interact(chef);
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        if(chef.getIndex() == 0){
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
                chefSelect();
            }
        }
        else if (chef.getIndex() == 1) {
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
                chefSelect();
            }
        }
    }
}
