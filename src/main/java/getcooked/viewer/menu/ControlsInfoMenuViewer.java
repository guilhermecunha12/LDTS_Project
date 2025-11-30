package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.ControlsInfoMenu;
import getcooked.viewer.Viewer;

public class ControlsInfoMenuViewer extends Viewer<ControlsInfoMenu> {

    public ControlsInfoMenuViewer(ControlsInfoMenu controlsInfoMenu) {
        super(controlsInfoMenu);
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.changeBackgroundColor("#468499"); // background color
        gui.paint();
        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 4, gui.getHeight()/12), "Controls", "#ffcf40", bgColor);

        if (getModel().isOnePlayer()) draw1PlayerControls(gui);
        else draw2PlayerControls(gui);
        }


    private void draw1PlayerControls(GUI gui) {
        int w = gui.getWidth();
        int h = gui.getHeight();
        String bgColor = gui.getBackgroundColor();
        Position wPos = new Position(w/6, h/3);
        Position sPos = new Position(wPos.getX(), wPos.getY() + 3);
        Position aPos = new Position(sPos.getX() - 3, sPos.getY());
        Position dPos = new Position(sPos.getX() + 3, sPos.getY());

        Position spacePos = new Position(w/4, 2*h/3);

        int i = 0;
        for (; i < 5; i++) { // Player 1 keys
            Position curPos = wPos;
            if (i == 1) curPos = aPos;
            else if (i == 2) curPos = sPos;
            else if (i == 3) curPos = dPos;
            else if (i == 4) curPos = spacePos;

            gui.drawTextBox(curPos, getModel().getEntry(i), "#ffffff", "#000000", 1);
        }


        for (; i < 9; i++) { // Player 1 controls
            Position curPos = new Position(7*w/12, wPos.getY() + (i-5) * 3);
            gui.drawText(curPos, getModel().getEntry(i), "#ffffff", bgColor);
        }

        // ESC control
        gui.drawText(new Position(1, 1), getModel().getEntry(getModel().getNumberEntries() - 1),
                "#ffffff", bgColor);
    }

    private void draw2PlayerControls(GUI gui) {
        int w = gui.getWidth();
        int h = gui.getHeight();
        String bgColor = gui.getBackgroundColor();
        Position player1Pos = new Position(w/4 - 7, 6);
        Position player2Pos = new Position(3*w/4 - 8, 6);
        gui.drawText(player1Pos, "Player 1 (WASD)", "#ffffff",bgColor);
        gui.drawText(player2Pos, "Player 2 (Arrows)", "#ffffff", bgColor);

        Position wPos = new Position(w/8, 7*h/24);
        Position sPos = new Position(wPos.getX(), wPos.getY() + 3);
        Position aPos = new Position(sPos.getX() - 3, sPos.getY());
        Position dPos = new Position(sPos.getX() + 3, sPos.getY());

        Position spacePos = new Position(7*w/24, 5*h/12);

        int i = 0;
        for (; i < 5; i++) { // Player 1 keys
            Position curPos = wPos;
            if (i == 1) curPos = aPos;
            else if (i == 2) curPos = sPos;
            else if (i == 3) curPos = dPos;
            else if (i == 4) curPos = spacePos;

            gui.drawTextBox(curPos, getModel().getEntry(i), "#ffffff", "#000000", 1);
        }

        for (; i < 10; i++) { // Player 1 controls
            Position curPos = new Position(w/8, 7*h/12 + (i-5) * 3);
            gui.drawText(curPos, getModel().getEntry(i), "#ffffff", bgColor);
        }


        Position upPos = new Position(2*w/3, 11*h/36);
        Position downPos = new Position(upPos.getX(), upPos.getY() + 3);
        Position leftPos = new Position(downPos.getX() - 3, downPos.getY());
        Position rightPos = new Position(downPos.getX() + 3, downPos.getY());

        Position enterPos = new Position(19*w/24, 7*h/24);

        for (; i < 15; i++) { // Player 2 Controls
            Position curPos = upPos;
            if (i == 11) curPos = leftPos;
            else if (i == 12) curPos = downPos;
            else if (i == 13) curPos = rightPos;
            else if (i == 14) curPos = enterPos;

            gui.drawTextBox(curPos, getModel().getEntry(i), "#ffffff", "#000000", 1);
        }

        for (; i < 20; i++) { // Player 1 controls
            Position curPos = new Position(7*w/12, 7*h/12 + (i-15) * 3);
            gui.drawText(curPos, getModel().getEntry(i), "#ffffff", bgColor);
        }

        // ESC control
        gui.drawText(new Position(1, 1), getModel().getEntry(i),"#ffffff", bgColor);
    }
}
    

