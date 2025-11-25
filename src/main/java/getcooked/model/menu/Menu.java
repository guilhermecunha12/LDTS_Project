package getcooked.model.menu;

import java.util.List;

abstract public class Menu {
    protected List<String> entries;
    protected int currentEntry = 0;

    protected Menu(List<String> entries) {
        this.entries = entries;
    }

    public void nextEntry() {
        currentEntry++;
        if(currentEntry >= entries.size()) {
            currentEntry = 0;
        }
    }

    public void previousEntry() {
        currentEntry--;
        if(currentEntry < 0) {
            currentEntry = entries.size() - 1;
        }
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
