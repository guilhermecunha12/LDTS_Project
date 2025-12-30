package getcooked.model.game;


import getcooked.model.game.dish.Dish;

import java.util.Objects;

public class Order {

    private final Dish desiredDish;
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
        if (remainingTime > 0){
            remainingTime--;
        }
    }

    public boolean isExpired(){
        return remainingTime <= 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null) return false;
        if (!(obj instanceof Order order)) return false;

        return this.desiredDish.equals(order.desiredDish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desiredDish);
    }
}
