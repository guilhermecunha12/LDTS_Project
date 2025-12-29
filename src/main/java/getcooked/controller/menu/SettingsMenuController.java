package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.menu.MainMenu;
import getcooked.model.menu.SettingsMenu;
import getcooked.sound.AudioManager;
import getcooked.states.menu.MainMenuState;

import java.io.IOException;
import java.util.Set;

public class SettingsMenuController extends Controller<SettingsMenu> {

    public SettingsMenuController(SettingsMenu settingsMenu) {
        super(settingsMenu);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        for (GUI.ACTION action : actions) {
            switch (action) {
                case PLAYER2_UP: getModel().previousEntry(); break;
                case PLAYER2_DOWN: getModel().nextEntry(); break;
                case PLAYER2_LEFT: volumeDecrease(game); break;
                case PLAYER2_RIGHT: volumeIncrease(game); break;
                case PLAYER2_SELECT: selectOption(game); break;
                default: break;
            }
        }
    }

    private void volumeDecrease(Game game) {
        if (getModel().isSelectedBackgroundVolume()) {
            AudioManager audioManager = game.getAudioManager();
            int newVolume = Math.max(0, audioManager.getCurrentPercentage() - 5);
            audioManager.setBackgroundVolume(newVolume);
            getModel().updateVolumePercentage();
        }
    }

    private void volumeIncrease(Game game) {
        if (getModel().isSelectedBackgroundVolume()) {
            AudioManager audioManager = game.getAudioManager();
            int newVolume = Math.min(100, audioManager.getCurrentPercentage() + 5);
            audioManager.setBackgroundVolume(newVolume);
            getModel().updateVolumePercentage();
        }
    }

    private void selectOption(Game game){
        if (getModel().isSelectedVolume()) {
            toggleBackgroundMusic(game);
        }
        if (getModel().isSelectedBack()) {
            game.changeState(new MainMenuState(new MainMenu()));
        }
    }

    private void toggleBackgroundMusic(Game game) {
        AudioManager audioManager = game.getAudioManager();
        if (audioManager.isBackgroundMusicOn()) {
            audioManager.stopBackgroundMusic();
        } else {
            audioManager.playBackgroundMusic();
        }
        getModel().updateVolumeState();
    }
}
