package getcooked.model.menu;

import getcooked.sound.AudioManager;

import java.util.Arrays;

public class SettingsMenu extends Menu {
    private final AudioManager audioManager;

    public SettingsMenu(AudioManager audioManager) {
        super(Arrays.asList(audioManager.isBackgroundMusicOn()? "Background Music ON" : "Background Music OFF", "Back")); // logical entries
        this.audioManager = audioManager;
    }

    public void updateEntries() {
        if (audioManager.isBackgroundMusicOn()) entries.set(0, "Background Music ON");
        else entries.set(0, "Background Music OFF");
    }

    public boolean isSelectedVolume() { return isSelected(0); }
    public boolean isSelectedBack() { return isSelected(1); }
}