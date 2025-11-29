package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.Trash;

public class TrashViewer implements ElementViewer<Trash> {
    @Override
    public void draw(Trash trash, GUI gui) {
        gui.drawTrash(trash.getPosition());
    }
}
