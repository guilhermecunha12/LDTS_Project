package getcooked.model.game.kitchen.elements.interactable.cookware;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.grabable.Grabable;
import getcooked.model.game.kitchen.elements.grabable.Plate;
import getcooked.model.game.kitchen.elements.grabable.food.Food;
import getcooked.model.game.kitchen.elements.interactable.Interactable;

import java.util.ArrayList;
import java.util.List;

public abstract class Cookware extends Interactable {

    protected List<Food> contents = new ArrayList<>();
    protected int capacity;
    protected boolean cooked = false;

    public Cookware(int x, int y, int capacity) {
        super(x, y);
        this.capacity = capacity;
    }

    public List<Food> getContents() {
        return contents;
    }

    public boolean isCooked() {
        return cooked;
    }

    public boolean isFull(){
        return contents.size() == capacity;
    }

    public boolean isEmpty(){
        return contents.isEmpty();
    }

    public void clear(){
        contents.clear();
    }

    public boolean tryAdd(Grabable g) {
        if (g == null) return false;
        if (!(g instanceof Food)) return false;
        Food food = (Food) g;
        if (isFull()) return false;
        if (!accepts(food)) return false;
        contents.add(food);
        tryCook();
        return true;
    }

    @Override
    public void interact(Chef chef) {
        if (chef == null) return;
        if (chef.isHoldingSomething()){
            Grabable held = chef.getItem();

            if (held instanceof Food) {
                boolean added = tryAdd(held);
                if (added) {
                    chef.drop();
                }
                return;
            }

            if (held instanceof Plate) {
                Grabable result = createFinishedDish();
                if (result == null) return;
                Plate plate = (Plate) held;
                boolean placedOnPlate = plate.put(result);
            }
        }
    }
    protected abstract boolean accepts(Food food);
    protected abstract void tryCook();
    protected abstract Grabable createFinishedDish();
}
