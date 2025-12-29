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
    private final BufferedImage image;

    public Sprite(String filepath) throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(filepath);
        this.image = ImageIO.read(Objects.requireNonNull(resource));
    }

    public BufferedImage getImage() {
        return image;
    }

    public void draw(GUI gui, int x, int y, int xOffset, int yOffset)
            throws IOException, URISyntaxException, FontFormatException {
        x = x * SCALE + xOffset;
        y = y * SCALE + yOffset;
        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int argb = image.getRGB(dx, dy);
                if (getTransparency(argb) == 0)
                    continue;
                gui.drawPixel(x + dx, y + dy, getRGB(argb));
            }
        }
    }

    private static int getTransparency(int argb) {
        return argb >> 24;
    }

    private static TextColor getRGB(int argb) {
        int red = (argb >> 16) & 0xFF;
        int green = (argb >> 8) & 0xFF;
        int blue = argb & 0xFF;
        return new TextColor.RGB(red, green, blue);
    }
}
