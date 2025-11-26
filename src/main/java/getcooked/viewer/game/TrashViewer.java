package getcooked.viewer.game;

import getcooked.gui.GUI;

public class TrashViewer implements ElementViewer<Trash> {
    @Override
    public void draw(Trash trash, GUI gui) {
        gui.drawTrash(trash.getPosition());
    }
}
