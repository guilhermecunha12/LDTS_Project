package getcooked.viewer;

import getcooked.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SpriteTest {
    private Sprite sprite;

    @BeforeEach
    void setUp() throws Exception {
        sprite = new Sprite("sprites/test_sprite.png");
    }

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {
        GUI gui = mock(GUI.class);

        sprite.draw(gui,0,0,0,0);

        verify(gui,times(144)).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawWithOffset() throws IOException, URISyntaxException, FontFormatException {
        GUI gui = mock(GUI.class);

        sprite.draw(gui,1,1,5,5);

        verify(gui,times(144)).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testGetImage() {
        assertNotNull(sprite.getImage());
    }
}
