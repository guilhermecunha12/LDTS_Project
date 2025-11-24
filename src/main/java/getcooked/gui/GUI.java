package getcooked.gui;
import getcooked.model.Position;

public interface GUI { // intermediate class to represent a general GUI
    enum ACTION {UP, DOWN, RIGHT, LEFT, SELECT, NONE}

    ACTION getNextAction();
    void drawText(); // for menu's text
    void drawTextBox();

    // draw elements of the game
    void drawChef(Position position);
    void drawOrder(Position position);
    void drawTrash(Position position);
    void drawDelivery(Position position);
    void drawCounter(Position position);
    void drawSodaDispenser(Position position);
    void drawKebabDispenser(Position position);
    void drawSaladDispenser(Position position);
    void drawCarrotDispenser(Position position);
    void drawPlateDispenser(Position position);
    void drawPotatoDispenser(Position position);
    void drawRawBeefDispenser(Position position);
    void drawSoda(Position position);
    void drawKebab(Position position);
    void drawSalad(Position position);
    void drawCarrot(Position position);
    void drawChoppedCarrot(Position position);
    void drawPlate(Position position);
    void drawPotato(Position position);
    void drawChoppedPotato(Position position);
    void drawFriedPotato(Position position);
    void drawBurntPotato(Position position);
    void drawRawBeef(Position position);
    void drawGrilledBeef(Position position);
    void drawBurntBeef(Position position);
    void drawFryer(Position position);
    void drawPot(Position position);
    void drawGrill(Position position);
    void drawCutter(Position position);

    void clear();
    void refresh();
    void close();
}
