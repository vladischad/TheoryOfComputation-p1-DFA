package fa.dfa;

import fa.State;
import java.util.HashMap;
import java.util.Map;

/**
 * DFAState represents a state in a DFA and manages its transitions.
 * Each state maintains a mapping of input symbols to next states.
 * 
 * @author Reggie Wade, Vlad Maliutin
 */
public class DFAState extends State {
    private Map<Character, DFAState> transitions;

    /* 
     * Call super constructor to 
     * assign the state a name.
     * Creates a hashmap to store
     * transitions to other states.
     */
    public DFAState(String name) {
        super(name);
        this.transitions = new HashMap<>();
    }

    /**
	 * Adds the transition to the DFA's delta data structure
	 * @param symbol is the symbol to transition to toState
	 * @param toState is the label of the state where the transition ends
	 */
    public void addTransition(char symbol, DFAState toState) {
        transitions.put(symbol, toState);
    }

    /**
	 * Gets the next state based on the transition symbol
	 * @param symbol is the symbol to transition to toState
	 * @return the next state from the symbol (null if non existent)
	 */
    public DFAState getNextState(char symbol) {
        return transitions.get(symbol);
    }
}
