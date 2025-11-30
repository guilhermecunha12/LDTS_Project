package getcooked.model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class KitchenModelStorageTest {
    @BeforeEach
    void setUp() {
        KitchenModelStorage.storeKitchen(null);
    }

    @Test
    void testStoresAndRetrievesKitchen() {
        Kitchen mockedKitchen = mock(Kitchen.class);
        KitchenModelStorage.storeKitchen(mockedKitchen);
        assertEquals(mockedKitchen, KitchenModelStorage.getStoredKitchen());
    }
}
