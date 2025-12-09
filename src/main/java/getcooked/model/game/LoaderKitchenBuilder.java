package getcooked.model.game;

import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.model.game.elements.interactable.cookware.Cutter;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.model.game.elements.interactable.Trash;
import getcooked.model.game.elements.interactable.cookware.Pot;
import getcooked.model.game.elements.interactable.dispenser.CarrotDispenser;
import getcooked.model.game.elements.interactable.dispenser.PlateDispenser;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderKitchenBuilder extends KitchenBuilder{
    private final List<String> lines;

    public LoaderKitchenBuilder(int level, int numPlayers, int lives, int ordersDelivered) throws IOException{
        super(level, numPlayers, lives, ordersDelivered);

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

    @Override // public for testing
    public int getWidth() {
        int width = 0;
        for(String line : lines) {
            width = Math.max(width, line.length());
        }
        return width;
    }

    @Override // public for testing
    public int getHeight() {
        return lines.size();
    }

    @Override // public for testing
    public List<Tile> createTiles() {
        List<Tile> tiles = new ArrayList<>();
        for(int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for(int x = 0; x < line.length(); x++) {
                // Creates interactable based on the symbol
                switch (line.charAt(x)) {
                    case ' ': tiles.add(new Floor(x, y)); break;
                    case '#' : tiles.add(new Counter(x, y)); break;
                    case 'p':
                        tiles.add(new PotatoDispenser(x, y));
                        this.addAvailableDishType(DishType.POTATO_SOUP); // for generating orders
                        //TODO: this.addavailableDishType(Dish.DishType.FRIED_POTATO);
                        break;
                    case 'c':
                        tiles.add(new CarrotDispenser(x, y));
                        this.addAvailableDishType(DishType.CARROT_SOUP); // for generating orders
                        break;
                    case 'P': tiles.add(new PlateDispenser(x, y)); break;
                    case 'T' : tiles.add(new Trash(x, y)); break;
                    case 'D' : tiles.add(new DeliverySpot(x, y)); break;
                    case 'Q' : tiles.add(new Pot(x, y)); break;
                    case 'K' : tiles.add(new Cutter(x, y)); break;
                }
            }
        }
        return tiles;
    }
}