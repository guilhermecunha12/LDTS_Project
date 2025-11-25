package getcooked.model.game.kitchen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderKitchenBuilder extends KitchenBuilder{
    private final int level;
    private final List<String> lines;

    public LoaderKitchenBuilder(int level) throws IOException{
        // After the level 10 the kitchen to be drawn is always the same
        this.level = Math.min(level, 10);

        URL resource = LoaderKitchenBuilder.class.getResource("/kitchen/level" + this.level + ".lvl");

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for(String line; (line = br.readLine()) != null; ) {
            lines.add(line);
        }
        return lines;
    }


    @Override
    protected int getWidth() {
        int width = 0;
        for(String line : lines) {
            width = Math.max(width, line.length());
        }
        return width;
    }

    @Override
    protected int getHeight() {
        return lines.size();
    }
}
