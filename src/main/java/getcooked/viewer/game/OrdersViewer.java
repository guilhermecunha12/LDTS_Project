package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Order;
import getcooked.model.game.dish.DishType;
import getcooked.viewer.Sprite;
import getcooked.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static getcooked.Game.FPS;
import static getcooked.viewer.Sprite.SCALE;

public class OrdersViewer extends Viewer<List<Order>> {

    public OrdersViewer(List<Order> activeOrders) {
        super(activeOrders);
    }

    @Override
    public void drawElements(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        List<Order> activeOrders = getModel();

        gui.drawText(new Position(270,  60), "Orders: ", "#ffffff");
        int ySpacing = 20;
        for (int i = 0; i < activeOrders.size(); i++) { // iterate through the current active orders
            DishType curDishType = activeOrders.get(i).getDesiredDish().getType();
            Sprite sprite = switch (curDishType) {
                case POTATO_SOUP -> new Sprite("sprites/order_potato_soup.png");
                case CARROT_SOUP -> new Sprite("sprites/order_carrot_soup.png");
                case SALAD -> new Sprite("sprites/order_salad.png");
                case SODA -> new Sprite("sprites/order_soda.png");
                case FRIES -> new Sprite("sprites/order_fries.png");
                case KEBAB -> new Sprite("sprites/order_kebab.png");
                case STEAK -> new Sprite("sprites/order_steak.png");
                default -> throw new IllegalStateException("Unexpected value: " + curDishType);
            };

            sprite.draw(gui, 270 / SCALE,70 / SCALE, 6, 10 + ySpacing * i);

            Position curPos = new Position(271, (72 + ySpacing * i));
            gui.drawText(curPos, formatString(curDishType.toString()), "#000000");
            gui.drawText(new Position(curPos.getX(), curPos.getY() + 9),
            String.valueOf(activeOrders.get(i).getRemainingTime() / FPS), "#000000");
        }
    }

    private String formatString(String string) {
        return string.replace("_", " ");
    }
}
