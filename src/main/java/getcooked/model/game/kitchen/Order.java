package getcooked.model.game.kitchen;


import getcooked.model.game.kitchen.elements.grabable.Dish;

public class Order {

    private final Dish desiredDish;
    //Remaining time in seconds
    private int remainingTime;

    public Order(Dish desiredDish, int timeLimit) {
        this.desiredDish = desiredDish;
        this.remainingTime = timeLimit;
    }

    public Dish getDesiredDish() {
        return desiredDish;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void tick(){
        if(remainingTime > 0){
            remainingTime--;
        }
    }

    public boolean isExpired(){
        return remainingTime <= 0;
    }
}

