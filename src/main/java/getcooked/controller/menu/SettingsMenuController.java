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
                case PLAYER2_UP:
                    getModel().previousEntry();
                    break;
                case PLAYER2_DOWN:
                    getModel().nextEntry();
                    break;
                case PLAYER2_LEFT:
                    if (getModel().isSelectedBackgroundVolume()) {
                        AudioManager audioManager = game.getAudioManager();
                        int currentVolume = audioManager.getCurrentPercentage();
                        int newVolume = Math.max(0, currentVolume - 5);  // decrease by 5%
                        audioManager.setBackgroundVolume(newVolume);
                        getModel().updateVolumePercentage();
                    }
                    break;
                case PLAYER2_RIGHT:
                    if (getModel().isSelectedBackgroundVolume()) {
                        AudioManager audioManager = game.getAudioManager();
                        int currentVolume = audioManager.getCurrentPercentage();
                        int newVolume = Math.min(100, currentVolume + 5);  // increase by 1%
                        audioManager.setBackgroundVolume(newVolume);
                        getModel().updateVolumePercentage();
                    }
                    break;
                case PLAYER2_SELECT:
                    if (getModel().isSelectedVolume()) {
                        AudioManager audioManager = game.getAudioManager();
                        if (audioManager.isBackgroundMusicOn()) {
                            audioManager.stopBackgroundMusic();
                            getModel().updateVolumeState();
                        }
                        else {
                            audioManager.playBackgroundMusic();
                            getModel().updateVolumeState();
                        }
                    }
                    if (getModel().isSelectedBack()) game.changeState(new MainMenuState(new MainMenu()));
                    break;
                default:
                    break;
            }
        }
    }
}