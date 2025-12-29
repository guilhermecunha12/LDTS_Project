package getcooked.model.game;

import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Interactable;
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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LoaderKitchenBuilder extends KitchenBuilder{
    private final List<String> lines;

    public LoaderKitchenBuilder(int level, int numPlayers, int lives, int ordersDelivered) throws IOException{
        super(level, numPlayers, lives, ordersDelivered);

        URL resource = LoaderKitchenBuilder.class.getResource("/kitchen/level" + this.level + ".lvl");
        BufferedReader br = Files.newBufferedReader(Paths.get(Objects.requireNonNull(resource).getFile()), UTF_8);
        lines = readLines(br);
    }

    private static List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for(String line; (line = br.readLine()) != null; ) {
            lines.add(line);
        }
        return lines;
    }

    @Override // public for testing
    public int getWidth() {
        return lines.getFirst().length();
    }

    @Override // public for testing
    public int getHeight() {
        return lines.size();
    }

    @Override // public for testing
    public List<Interactable> createInteractables() {
        List<Interactable> interactables = new ArrayList<>();

        for(int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for(int x = 0; x < line.length(); x++) {
                switch (line.charAt(x)) {
                    case ' ': interactables.add(new Floor(x, y)); break;
                    case 'u': interactables.add(new Counter(x, y, 'u')); break;
                    case 'd': interactables.add(new Counter(x, y, 'd')); break;
                    case 'l': interactables.add(new Counter(x, y, 'l')); break;
                    case 'r': interactables.add(new Counter(x, y, 'r')); break;
                    case '1': interactables.add(new Counter(x, y, '1')); break;
                    case '2': interactables.add(new Counter(x, y, '2')); break;
                    case '3': interactables.add(new Counter(x, y, '3')); break;
                    case '4': interactables.add(new Counter(x, y, '4')); break;
                    case 'P': interactables.add(new PlateDispenser(x, y)); break;
                    case 'T': interactables.add(new Trash(x, y)); break;
                    case 'K': interactables.add(new CounterCutter(x, y)); break;
                    case 'B': interactables.add(new BeefDispenser(x, y)); break;

                    case 'D':
                        DeliverySpot deliverySpot = new DeliverySpot(x, y);
                        interactables.add(deliverySpot);
                        deliverySpots.add(deliverySpot);
                        break;

                    case 'p':
                        interactables.add(new PotatoDispenser(x, y));
                        this.addAvailableDishType(DishType.POTATO_SOUP); // for generating orders
                        break;
                    case 'c':
                        interactables.add(new CarrotDispenser(x, y));
                        this.addAvailableDishType(DishType.CARROT_SOUP);
                        break;
                    case 'S':
                        interactables.add(new SaladDispenser(x, y));
                        this.addAvailableDishType(DishType.SALAD);
                        break;
                    case 'G':
                        Grill grill = new Grill(x, y);
                        interactables.add(grill);
                        this.addAvailableDishType(DishType.STEAK);
                        cookwares.add(grill);
                        break;
                    case 's':
                        interactables.add(new SodaDispenser(x, y));
                        this.addAvailableDishType(DishType.SODA);
                        break;
                    case 'k':
                        interactables.add(new KebabCutter(x, y));
                        this.addAvailableDishType(DishType.KEBAB);
                        break;
                    case 'F':
                        Fryer leftFryer = new Fryer(x, y,'F');
                        interactables.add(leftFryer);
                        cookwares.add(leftFryer);
                        this.addAvailableDishType(DishType.FRIES);
                        break;
                    case 'f':
                        Fryer rightFryer = new Fryer(x, y,'f');
                        interactables.add(rightFryer);
                        cookwares.add(rightFryer);
                        break;
                    case 'Q':
                        Pot pot = new Pot(x, y);
                        interactables.add(pot);
                        cookwares.add(pot);
                        break;

                    default: break;
                }
            }
        }
        return interactables;
    }
}
