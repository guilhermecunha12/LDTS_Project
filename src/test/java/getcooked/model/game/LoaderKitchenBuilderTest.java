package getcooked.model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoaderKitchenBuilderTest {
    private LoaderKitchenBuilder loaderKitchenBuilder;

    @BeforeEach
    void setUp() throws Exception {
        loaderKitchenBuilder = new LoaderKitchenBuilder(1, 1, 3);
    }

    @Test
    void testGetWidth() {
        // All levels have 22 of width
        assertEquals(22, loaderKitchenBuilder.getWidth());
    }

    @Test
    void testGetHeight() {
        // All levels have 17 of height
        assertEquals(17, loaderKitchenBuilder.getHeight());
    }

    @Test
    void testCreateTiles() {
        assertEquals(22*17, loaderKitchenBuilder.createTiles().size());
    }

}
