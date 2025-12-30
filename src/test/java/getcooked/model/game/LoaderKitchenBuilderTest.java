package getcooked.model.game;

import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Interactable;
import getcooked.model.game.elements.interactable.*;
import getcooked.model.game.elements.interactable.dispenser.*;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Fryer;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Grill;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.model.game.elements.interactable.kitchenware.cutter.KebabCutter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoaderKitchenBuilderTest {

    @Test
    void testConstructorLevel10() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        assertNotNull(builder);
    }

    @Test
    void testGetWidth() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        int width = builder.getWidth();
        assertTrue(width > 0);
    }

    @Test
    void testGetHeight() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        int height = builder.getHeight();
        assertTrue(height > 0);
    }

    @Test
    void testCreateInteractables() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertNotNull(interactables);
        assertFalse(interactables.isEmpty());
    }

    @Test
    void testCreateKitchen() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        Kitchen kitchen = builder.createKitchen();
        assertNotNull(kitchen);
        assertEquals(10, kitchen.getLevel());
        assertEquals(2, kitchen.getNumPlayers());
        assertEquals(3, kitchen.getLives());
    }

    @Test
    void testFloorCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(Floor.class::isInstance));
    }

    @Test
    void testCounterUpCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == 'u'));
    }

    @Test
    void testCounterDownCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == 'd'));
    }

    @Test
    void testCounterLeftCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == 'l'));
    }

    @Test
    void testCounterRightCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == 'r'));
    }

    @Test
    void testCounterCorner1Created() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == '1'));
    }

    @Test
    void testCounterCorner2Created() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == '2'));
    }

    @Test
    void testCounterCorner3Created() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == '3'));
    }

    @Test
    void testCounterCorner4Created() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Counter && ((Counter) i).getType() == '4'));
    }

    @Test
    void testPlateDispenserCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(PlateDispenser.class::isInstance));
    }

    @Test
    void testTrashCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(Trash.class::isInstance));
    }

    @Test
    void testCounterCutterCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(CounterCutter.class::isInstance));
    }

    @Test
    void testBeefDispenserCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(BeefDispenser.class::isInstance));
    }

    @Test
    void testDeliverySpotCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(DeliverySpot.class::isInstance));
    }

    @Test
    void testPotatoDispenserCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(PotatoDispenser.class::isInstance));
    }


    @Test
    void testCarrotDispenserCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(CarrotDispenser.class::isInstance));
    }

    @Test
    void testSaladDispenserCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(SaladDispenser.class::isInstance));
    }

    @Test
    void testGrillCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(Grill.class::isInstance));
    }

    @Test
    void testSodaDispenserCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(SodaDispenser.class::isInstance));
    }

    @Test
    void testKebabCutterCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(KebabCutter.class::isInstance));
    }

    @Test
    void testFryerLeftCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Fryer && ((Fryer) i).getType() == 'F'));
    }

    @Test
    void testFryerRightCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(i -> i instanceof Fryer && ((Fryer) i).getType() == 'f'));
    }
    @Test
    void testPotCreated() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(10, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.stream().anyMatch(Pot.class::isInstance));
    }

    @Test
    void testInvalidInteractables() throws IOException {
        LoaderKitchenBuilder builder = new LoaderKitchenBuilder(0, 2, 3, 0);
        List<Interactable> interactables = builder.createInteractables();
        assertTrue(interactables.isEmpty());
    }
}
