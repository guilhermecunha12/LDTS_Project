package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.ControlsInfoMenu;
import getcooked.viewer.Sprite;
import getcooked.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class ControlsInfoMenuViewer extends Viewer<ControlsInfoMenu> {

    private static final String TEXT_COLOR = "#ffffff";

    public ControlsInfoMenuViewer(ControlsInfoMenu controlsInfoMenu) {
        super(controlsInfoMenu);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        gui.changeBackgroundColor("#468499");
        gui.paint();

        int middleX = gui.getWidth() / 2;
        gui.drawText(new Position(middleX - 16, gui.getHeight()/12), "Controls", "#ffcf40");
        gui.drawText(new Position(1, 1), "ESC - Escape Menu", TEXT_COLOR);

        if (getModel().isOnePlayer()) draw1PlayerControls(gui);
        else draw2PlayerControls(gui);
    }


    private void draw1PlayerControls(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int middleX = gui.getWidth() / 2;
        int middleY = gui.getHeight() / 2;

        Position wPosMeaning = new Position(middleX + 30,middleY - 30);
        Position sPosMeaning = new Position(middleX + 30, middleY- 22);
        Position aPosMeaning = new Position(middleX + 30,middleY - 14);
        Position dPosMeaning = new Position(middleX + 30,middleY - 6);
        Position spacePosMeaning = new Position(middleX + 30,middleY + 2);

        for (int i = 0; i < 5; i++) { // Player 1 keys
            Position curPos = wPosMeaning;
            if (i == 1) curPos = aPosMeaning;
            if (i == 2) curPos = sPosMeaning;
            else if (i == 3) curPos = dPosMeaning;
            else if (i == 4) curPos = spacePosMeaning;
            gui.drawText(curPos, getModel().getEntry(i), TEXT_COLOR);
        }

        Sprite spriteControls = new Sprite("sprites/wasdcontrols.png");
        Sprite spriteSpace = new Sprite("sprites/space.png");
        spriteControls.draw(gui, (middleX - 70) / SCALE, (middleY - 25) / SCALE, 0, 0);
        spriteSpace.draw(gui,  (middleX - 70) / SCALE,(middleY + 5) / SCALE, 0, 0);

    }

    private void draw2PlayerControls(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int middleX = gui.getWidth() / 2;
        int h = gui.getHeight();
        int middleY = gui.getHeight() / 2;

        Position wPosMeaning = new Position(middleX - 100,h - 70);
        Position sPosMeaning = new Position(middleX - 100, h - 62);
        Position aPosMeaning = new Position(middleX - 100,h - 54);
        Position dPosMeaning = new Position(middleX - 100,h - 46);
        Position spacePosMeaning = new Position(middleX - 100,h - 38);
        Position upPosMeaning = new Position(middleX + 45,h- 70);
        Position downPosMeaning = new Position(middleX + 45, h - 62);
        Position leftPosMeaning = new Position(middleX + 45,h- 54);
        Position rightPosMeaning = new Position(middleX + 45,h- 46);
        Position enterPosMeaning = new Position(middleX + 45,h - 38);

        for (int i = 0; i < 10; i++) {
            Position curPos = wPosMeaning;
            if (i == 1) curPos = aPosMeaning;
            else if (i == 2) curPos = sPosMeaning;
            else if (i == 3) curPos = dPosMeaning;
            else if (i == 4) curPos = spacePosMeaning;
            else if (i == 5) curPos = upPosMeaning;
            else if (i == 6) curPos = leftPosMeaning;
            else if (i == 7) curPos = downPosMeaning;
            else if (i == 8) curPos = rightPosMeaning;
            else if (i == 9) curPos = enterPosMeaning;
            gui.drawText(curPos, getModel().getEntry(i), TEXT_COLOR);
        }
        Sprite spritePlayer1 = new Sprite("sprites/wasdcontrols.png");
        Sprite spriteSpace = new Sprite("sprites/space.png");
        spritePlayer1.draw(gui,  (middleX - 100) / SCALE, (middleY - 25) / SCALE, 0, 0);
        spriteSpace.draw(gui,  (middleX - 100) / SCALE,  (middleY + 5) / SCALE, 0, 0);
        Sprite spritePlayer2 = new Sprite("sprites/arrowscontrols.png");
        Sprite spriteEnter = new Sprite("sprites/enter.png");
        spritePlayer2.draw(gui,(middleX + 50) / SCALE,(middleY - 25) /SCALE, 0, 0);
        spriteEnter.draw(gui,(middleX + 65) / SCALE, (middleY + 5) / SCALE, 0, 0);
    }
}


