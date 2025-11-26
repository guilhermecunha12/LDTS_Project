package getcooked.model.game.kitchen.elements.grabable;

public class Plate extends Grabable {

    private Grabable content;

    public Plate(int x, int y) {
        super(x, y);
    }

    public boolean hasContent(){
        return content != null;
    }

    public Grabable getContent(){
        return content;
    }

    public boolean put(Grabable g) {
        if (g == null) return false;
        if (content != null) return false;
        content = g;
        return true;
    }
}
