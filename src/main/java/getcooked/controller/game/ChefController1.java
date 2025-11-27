package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;

import java.io.IOException;
import java.util.Set;

public class ChefController1 extends Controller<Kitchen> {
    private final Chef chef;

    public ChefController1(Chef chef, Kitchen kitchen) {
        super(kitchen);
        this.chef = chef;
    }

    public void moveChefLeft(){

    }

    public void moveChefUp(){

    }

    public void moveChefDown(){

    }

    public void moveChefRight(){

    }

    public void moveChef(){

    }

    public void ChefSelect(){

    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
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
}
