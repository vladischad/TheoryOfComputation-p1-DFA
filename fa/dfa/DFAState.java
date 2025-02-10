package fa.dfa;

import fa.State;
import java.util.HashMap;
import java.util.Map;

/**
 * DFAState represents a state in a DFA and manages its transitions.
 * Each state maintains a mapping of input symbols to next states.
 * 
 * @author 
 */
public class DFAState extends State {
    private Map<Character, DFAState> transitions;

    public DFAState(String name) {
        super(name);
        transitions = new HashMap<>();
    }

    public void addTransition(char symbol, DFAState toState) {
        transitions.put(symbol, toState);
    }

    public DFAState getNextState(char symbol) {
        return transitions.get(symbol);
    }
}
