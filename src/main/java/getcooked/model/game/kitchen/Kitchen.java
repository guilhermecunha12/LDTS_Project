package getcooked.model.game.kitchen;


public class Kitchen {
    private final int width;
    private final int height;
    private boolean escapeMenuOn;

    // Create the objects regarding the elements that are part of the kitchen


    public Kitchen(int width, int height) {
        this.width = width;
        this.height = height;
        this.escapeMenuOn = false;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public boolean isEscapeMenuOn() {
        return escapeMenuOn;
    }

}
