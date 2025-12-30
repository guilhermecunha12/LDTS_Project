package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.model.game.elements.Chef;
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
        moveChef(chef.getPosition().getLeft());
        chef.turnLeft();
    }

    public void moveChefUp(){
        moveChef(chef.getPosition().getUp());
        chef.turnUp();
    }

    public void moveChefDown(){
        moveChef(chef.getPosition().getDown());
        chef.turnDown();
    }

    public void moveChefRight(){
        moveChef(chef.getPosition().getRight());
        chef.turnRight();
    }

    public void moveChef(Position position){
        if(getModel().isEmpty(position)) chef.setPosition(position);
    }

    public void chefSelect(){
        Position toInteract = chef.getFrontPosition();
        Interactable interactableToInteract = getModel().getInteractable(toInteract);

        if (interactableToInteract != null) {
            interactableToInteract.interact(chef);
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        int idx = chef.getIndex();
        GUI.ACTION up = GUI.ACTION.PLAYER1_UP;
        GUI.ACTION down  = GUI.ACTION.PLAYER1_DOWN;
        GUI.ACTION left = GUI.ACTION.PLAYER1_LEFT;
        GUI.ACTION right  = GUI.ACTION.PLAYER1_RIGHT;
        GUI.ACTION select = GUI.ACTION.PLAYER1_SELECT;

        if (idx == 1) {
            up = GUI.ACTION.PLAYER2_UP;
            down = GUI.ACTION.PLAYER2_DOWN;
            left = GUI.ACTION.PLAYER2_LEFT;
            right = GUI.ACTION.PLAYER2_RIGHT;
            select = GUI.ACTION.PLAYER2_SELECT;
        }

        if (actions.contains(up)) moveChefUp();
        if (actions.contains(down)) moveChefDown();
        if (actions.contains(left)) moveChefLeft();
        if (actions.contains(right)) moveChefRight();
        if (actions.contains(select)) chefSelect();
    }
}
