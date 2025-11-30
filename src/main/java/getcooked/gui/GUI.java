package getcooked.gui;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import getcooked.model.Position;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Set;

public interface GUI { // intermediate class to represent a general GUI
    enum ACTION {PLAYER1_UP, PLAYER2_UP, PLAYER1_DOWN, PLAYER2_DOWN, PLAYER1_RIGHT,
        PLAYER2_RIGHT, PLAYER1_LEFT, PLAYER2_LEFT, PLAYER1_SELECT, PLAYER2_SELECT, ESCAPE}

    int getHeight();
    int getWidth();

    Set<ACTION> getNextAction();

    // draw elements of the game
    void drawFloor(Position position);
    void drawChef1(Position position);
    void drawChef2(Position position);
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
    void drawPotatoSoup(Position position);
    void drawCarrotSoup(Position position);

    void drawText(Position position, String text, String color, String bgColor);
    void drawTextBox(Position position, String text, String textColor, String bgColor, int padding);
    void drawRectangle(Position position, int width, int height, String color);

    void changeBackgroundColor(String newColor);
    void paint(); // paint whole gui with background color
    String getBackgroundColor();

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}
