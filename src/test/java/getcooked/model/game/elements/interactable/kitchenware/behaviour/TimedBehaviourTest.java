package getcooked.model.game.elements.interactable.kitchenware.behaviour;

import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TimedBehaviourTest {

    private TimedBehaviour timedBehaviour;
    private Kitchenware kitchenware;

    @BeforeEach
    void setUp() {
        this.timedBehaviour = new TimedBehaviour(5);
        this.kitchenware = mock(Kitchenware.class);
    }

    @Test
    void testExecuteFinished() {
        for (int i = 0; i < 5; i++) {
            timedBehaviour.execute(kitchenware);
        }
        verify(kitchenware, times(1)).finish();
    }
}