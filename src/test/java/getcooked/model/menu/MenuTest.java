package getcooked.model.menu;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuTest {
    private Menu menu;
    private List<String> entries;


    @BeforeEach
    void setUp() {
        this.entries = Arrays.asList("Option 1", "Option 2", "Option 3");
        menu = new Menu(entries) {};
    }

    @Test
    void testNextEntryGreaterThanSize(){
        // When we are in the last entry, it should wrap around
        menu.currentEntry = 2;
        menu.nextEntry();
        assertEquals(0, menu.currentEntry);
    }

    @Test
    void testPreviousEntryBeforeZero(){
        // When we are in the first entry, it should wrap around to the last
        menu.currentEntry = 0;
        menu.previousEntry();
        assertEquals(entries.size() - 1, menu.currentEntry);
    }

    @Test
    void testPreviousEntry(){
        // Normal previous entry
        menu.currentEntry = 2;
        menu.previousEntry();
        assertEquals(1, menu.currentEntry);
    }

    @Test
    void testNextEntry(){
        // Normal next entry
        menu.currentEntry = 0;
        menu.nextEntry();
        assertEquals(1, menu.currentEntry);
    }

}
