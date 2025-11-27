package getcooked.model.game.kitchen;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Floor;
import getcooked.model.game.kitchen.elements.Tile;
import getcooked.model.game.kitchen.elements.interactable.Counter;
import getcooked.model.game.kitchen.elements.interactable.DeliverySpot;
import getcooked.model.game.kitchen.elements.interactable.Interactable;
import getcooked.model.game.kitchen.elements.interactable.Trash;
import getcooked.model.game.kitchen.elements.interactable.cookware.Pot;
import getcooked.model.game.kitchen.elements.interactable.dispenser.PlateDispenser;
import getcooked.model.game.kitchen.elements.interactable.dispenser.PotatoDispenser;

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

    @Override
    protected List<Tile> createTiles() {
        List<Tile> tiles = new ArrayList<>();
        for(int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for(int x = 0; x < line.length(); x++) {
                // Creates interactable based on the symbol
                switch (line.charAt(x)) {
                    case ' ': tiles.add(new Floor(x, y)); break;
                    case '#' : tiles.add(new Counter(x, y)); break;
                    case 'p': tiles.add(new PotatoDispenser(x, y)); break;
                    case 'P': tiles.add(new PlateDispenser(x, y));
                    case 'T' : tiles.add(new Trash(x, y)); break;
                    case 'D' : tiles.add(new DeliverySpot(x, y)); break;
                    case 'Q' : tiles.add(new Pot(x, y));
                }
            }
        }
        return tiles;
    }

    @Override
    protected List<Chef> createChefs() {
        List<Chef> chefs = new ArrayList<>();
        for(int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) == 'C') chefs.add(new Chef(x, y));
            }
        }
        return chefs;
    }
}
