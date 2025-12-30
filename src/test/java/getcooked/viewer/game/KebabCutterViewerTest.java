package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cutter.KebabCutter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class KebabCutterViewerTest {
    private GUI gui;
    private KebabCutterViewer kebabCutterViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        kebabCutterViewer = new KebabCutterViewer();
    }

    @Test
    void testDrawKebabCutterNormal() throws IOException, URISyntaxException, FontFormatException {
        KebabCutter kebabCutter = mock(KebabCutter.class);

        when(kebabCutter.isFinished()).thenReturn(false);

        kebabCutterViewer.draw(kebabCutter, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawKebabCutterFinished() throws IOException, URISyntaxException, FontFormatException {
        KebabCutter kebabCutter = mock(KebabCutter.class);

        when(kebabCutter.isFinished()).thenReturn(true);

        kebabCutterViewer.draw(kebabCutter, gui);

        verify(gui, atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
