package getcooked.model.menu;

import java.util.Arrays;

public class SettingsMenu extends Menu {
    private boolean volumeOn;

    public SettingsMenu() {
        super(Arrays.asList("Volume ON", "Back")); // logical entries
        this.volumeOn = true;
    }

    public void toggleVolume() {
        // Changes the internal state of the volume
        this.volumeOn = !this.volumeOn;
        // Calls the methods that will perform those changes in the entries
        if(volumeOn){
            entries.set(0, "Volume ON");
        }
        else{
            entries.set(0, "Volume OFF");
        }
    }

    public boolean isSelectedVolume() { return isSelected(0); }
    public boolean isSelectedBack() { return isSelected(1); }
    public boolean isVolumeOn() { return volumeOn; }
}