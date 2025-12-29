package getcooked.model.game;

// Custom Singleton class, that only contains one static Kitchen member variable
public final class KitchenModelStorage {
    private static Kitchen storedKitchen;

    public static void storeKitchen(Kitchen newKitchen) {
        storedKitchen = newKitchen;
    }

    public static Kitchen getStoredKitchen() {
        return storedKitchen;
    }

    private KitchenModelStorage() {}
}
