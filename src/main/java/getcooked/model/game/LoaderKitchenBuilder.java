package getcooked.model.game;

import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.model.game.elements.interactable.kitchenware.cutter.KebabCutter;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.model.game.elements.interactable.Trash;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Fryer;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Grill;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import getcooked.model.game.elements.interactable.dispenser.*;

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

                    case 'u' : tiles.add(new Counter(x, y, 'u')); break;
                    case 'd' : tiles.add(new Counter(x, y, 'd')); break;
                    case 'l' : tiles.add(new Counter(x, y, 'l')); break;
                    case 'r' : tiles.add(new Counter(x, y, 'r')); break;
                    case '1' : tiles.add(new Counter(x, y, '1')); break;
                    case '2' : tiles.add(new Counter(x, y, '2')); break;
                    case '3' : tiles.add(new Counter(x, y, '3')); break;
                    case '4' : tiles.add(new Counter(x, y, '4')); break;

                    case 'p':
                        tiles.add(new PotatoDispenser(x, y));
                        this.addAvailableDishType(DishType.POTATO_SOUP); // for generating orders
                        break;
                    case 'c':
                        tiles.add(new CarrotDispenser(x, y));
                        this.addAvailableDishType(DishType.CARROT_SOUP); // for generating orders
                        break;
                    case 'S':
                        tiles.add(new SaladDispenser(x, y));
                        this.addAvailableDishType(DishType.SALAD);
                        break;
                    case 'B': tiles.add(new BeefDispenser(x, y)); break;
                    case 'G':
                        tiles.add(new Grill(x, y));
                        this.addAvailableDishType(DishType.STEAK);
                        break;
                    case 's':
                        tiles.add(new SodaDispenser(x, y));
                        this.addAvailableDishType(DishType.SODA);
                        break;
                    case 'k':
                        tiles.add(new KebabCutter(x, y));
                        this.addAvailableDishType(DishType.KEBAB);
                        break;
                    case 'F':
                        tiles.add(new Fryer(x, y,'F'));
                        this.addAvailableDishType(DishType.FRIES);
                        break;
                    case 'f':
                        tiles.add(new Fryer(x, y,'f'));
                        break;
                    case 'P': tiles.add(new PlateDispenser(x, y)); break;
                    case 'T' : tiles.add(new Trash(x, y)); break;
                    case 'D' : tiles.add(new DeliverySpot(x, y)); break;
                    case 'Q' : tiles.add(new Pot(x, y)); break;
                    case 'K' : tiles.add(new CounterCutter(x, y)); break;
                }
            }
        }
        return tiles;
    }
}