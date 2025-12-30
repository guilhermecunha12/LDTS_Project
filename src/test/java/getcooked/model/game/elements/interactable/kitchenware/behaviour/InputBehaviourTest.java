package getcooked.model.game.elements.interactable.kitchenware.behaviour;

import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InputBehaviourTest {
    private InputBehaviour inputBehaviour;
    private Kitchenware kitchenware;

    @BeforeEach
    void setUp() {
        this.inputBehaviour = new InputBehaviour(5);
        this.kitchenware = mock(Kitchenware.class);
    }

    @Test
    void testExecuteFinished() {
        for (int i = 0; i < 5; i++) {
            inputBehaviour.execute(kitchenware);
        }
        verify(kitchenware, times(1)).finish();

    }
}