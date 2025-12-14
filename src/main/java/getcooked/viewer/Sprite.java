package getcooked.viewer;

import com.googlecode.lanterna.TextColor;
import getcooked.gui.GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class Sprite {
    public static final int SCALE = 12;
    private BufferedImage image;

    public Sprite(String filepath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filepath);
        this.image = ImageIO.read(Objects.requireNonNull(resource));
    }

    public BufferedImage getImage() {
        return image;
    }

    public void draw(GUI gui, double x, double y) throws IOException, URISyntaxException, FontFormatException {
        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int ARGB = image.getRGB(dx, dy);
                if (getTransparency(ARGB) == 0)
                    continue;
                gui.drawPixel(x + dx, y + dy, getRGB(ARGB));
            }
        }
    }

    private int getTransparency(int ARGB) {
        return ARGB >> 24;
    }

    private TextColor getRGB(int ARGB) {
        int red = ARGB >> 16 & 0xFF;
        int green = ARGB >> 8 & 0xFF;
        int blue = ARGB & 0xFF;
        return new TextColor.RGB(red, green, blue);
    }
}