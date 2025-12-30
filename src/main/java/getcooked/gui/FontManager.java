package getcooked.gui;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FontManager {
    private int fontSize;
    private BufferedImage fontImage;
    private Map<Character, Coordinate> charPositionCache;

    public FontManager() throws IOException {
        loadFontResources();
        setFontSize();
    }

    private void setFontSize() {
        // total size of the screen
        try {
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice().getDefaultConfiguration().getBounds();
            fontSize = bestFontSize(screenBounds.height, screenBounds.width);
        } catch (HeadlessException e) {
            // Default size for headless mode (during testing)
            fontSize = bestFontSize(1080, 1920);
        }
    }

    public int bestFontSize(int height, int width) {
        // Function that maps fontSize for each resolution (considering area of the available screen)
        return Math.max(1, (int)Math.round(-40.78265 + 3.12953 * Math.log((double)height * (double)width)));
    }

    public AWTTerminalFontConfiguration loadPixelFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(Objects.requireNonNull(resource).toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        // Derive the pixel font size
        Font pixelFont = font.deriveFont(Font.PLAIN, this.fontSize);
        return AWTTerminalFontConfiguration.newInstance(pixelFont);
    }

    private void loadFontResources() throws IOException {
        // Load font image once
        URL image = getClass().getClassLoader().getResource("fonts/font.png");
        this.fontImage = ImageIO.read(Objects.requireNonNull(image));

        // Build character position cache once
        this.charPositionCache = new HashMap<>();
        URL resource = getClass().getClassLoader().getResource("fonts/font-map.txt");

        // load charPositionCache with the top left position of each character
        try (BufferedReader br = Files.newBufferedReader(Paths.get(Objects.requireNonNull(resource).getFile()), UTF_8)) {
            int mapY = 0;
            for (String line; (line = br.readLine()) != null; mapY++) {
                for (int mapX = 0; mapX < line.length(); mapX++) {
                    char c = line.charAt(mapX);
                    // font size is 3x5 px
                    int imgX = mapX * (3 + 1);
                    int imgY = mapY * (5 + 1);

                    charPositionCache.put(c, new Coordinate(imgX, imgY));
                }
            }
        }
    }

    public int getFontSize() {
        return fontSize;
    }

    public BufferedImage getFontImage() {
        return fontImage;
    }

    public Map<Character, Coordinate> getCharPositionCache() {
        return this.charPositionCache;
    }
}
