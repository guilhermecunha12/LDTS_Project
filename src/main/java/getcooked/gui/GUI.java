package getcooked.gui;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import getcooked.model.Position;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

public interface GUI { // intermediate class to represent a general GUI
    enum ACTION {PLAYER1_UP, PLAYER2_UP, PLAYER1_DOWN, PLAYER2_DOWN, PLAYER1_RIGHT,
        PLAYER2_RIGHT, PLAYER1_LEFT, PLAYER2_LEFT, PLAYER1_SELECT, PLAYER2_SELECT, ESCAPE}

    int getHeight();
    int getWidth();

    Set<ACTION> getNextAction();

    void drawText(Position position, String text, String color, String bgColor);
    void drawTextBox(Position position, String text, String textColor, String bgColor, int padding);
    void drawRectangle(Position position, int width, int height, String color);
    void drawPixel(double x, double y, TextColor color) throws IOException, URISyntaxException, FontFormatException;
    void changeBackgroundColor(String newColor);
    void paint(); // paint whole gui with background color
    String getBackgroundColor();
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}
