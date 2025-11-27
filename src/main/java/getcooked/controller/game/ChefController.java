package getcooked.controller.game;

import getcooked.controller.Controller;
import getcooked.model.Position;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;

public class ChefController extends Controller<Kitchen> {
    private final Chef chef;

    public ChefController(Chef chef, Kitchen kitchen) {
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

    public void moveChefSelect(){

    }

    public void modeChef(){

    }

    @Override
    public void step(getcooked.Game game, java.util.Set<getcooked.gui.GUI.ACTION> actions, long time) throws java.io.IOException {
        // passing a set with only one instruction
    }
}
