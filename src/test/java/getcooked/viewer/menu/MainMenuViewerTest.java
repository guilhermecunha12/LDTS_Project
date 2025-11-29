package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public class MainMenuViewerTest {
    private GUI gui;
    private MainMenu mainMenu;
    private MainMenuViewer mainMenuViewer;

    @BeforeEach
    void setUp() {
        gui = org.mockito.Mockito.mock(GUI.class);
        mainMenu = org.mockito.Mockito.mock(MainMenu.class);
        mainMenuViewer = new MainMenuViewer(mainMenu);
    }

    //TODO: implement tests for MainMenuViewer
}
