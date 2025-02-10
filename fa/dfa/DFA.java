package fa.dfa;

import fa.State;
import java.util.*;

/**
 * DFA class implements DFAInterface and represents a deterministic finite automaton.
 * It uses Java collections to manage states, transitions, and the alphabet.
 * 
 * @author 
 */
public class DFA implements DFAInterface {
    private Set<Character> sigma; // Alphabet
    private Map<String, DFAState> states; // State name to DFAState mapping
    private DFAState startState;
    private Set<DFAState> finalStates;
    
    public DFA() {
        sigma = new LinkedHashSet<>();
        states = new LinkedHashMap<>();
        finalStates = new HashSet<>();
    }

    @Override
    public boolean addState(String name) {
        if (states.containsKey(name)) return false;
        states.put(name, new DFAState(name));
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        DFAState state = states.get(name);
        if (state == null) return false;
        finalStates.add(state);
        return true;
    }

    @Override
    public boolean setStart(String name) {
        DFAState state = states.get(name);
        if (state == null) return false;
        startState = state;
        return true;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        return states.get(name);
    }

    @Override
    public boolean isFinal(String name) {
        DFAState state = states.get(name);
        return state != null && finalStates.contains(state);
    }

    @Override
    public boolean isStart(String name) {
        return startState != null && startState.getName().equals(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        if (!sigma.contains(onSymb) || !states.containsKey(fromState) || !states.containsKey(toState)) {
            return false;
        }
        states.get(fromState).addTransition(onSymb, states.get(toState));
        return true;
    }

    @Override
    public boolean accepts(String s) {
        if (startState == null) return false;
        DFAState currentState = startState;
        for (char c : s.toCharArray()) {
            currentState = currentState.getNextState(c);
            if (currentState == null) return false;
        }
        return finalStates.contains(currentState);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Q = { ");
        for (String state : states.keySet()) sb.append(state).append(" ");
        sb.append("}\n");

        sb.append("Sigma = { ");
        for (char sym : sigma) sb.append(sym).append(" ");
        sb.append("}\n");

        sb.append("delta =\n\t");
        for (char sym : sigma) sb.append(sym).append("\t");
        sb.append("\n");
        for (DFAState state : states.values()) {
            sb.append(state.getName()).append("\t");
            for (char sym : sigma) {
                DFAState nextState = state.getNextState(sym);
                sb.append(nextState != null ? nextState.getName() : "-").append("\t");
            }
            sb.append("\n");
        }

        sb.append("q0 = ").append(startState != null ? startState.getName() : "null").append("\n");

        sb.append("F = { ");
        for (DFAState state : finalStates) sb.append(state.getName()).append(" ");
        sb.append("}");

        return sb.toString();
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        DFA swappedDFA = new DFA();
        swappedDFA.sigma.addAll(sigma);
        for (String stateName : states.keySet()) {
            swappedDFA.addState(stateName);
        }
        for (String stateName : states.keySet()) {
            DFAState original = states.get(stateName);
            for (char sym : sigma) {
                char swappedSym = (sym == symb1) ? symb2 : (sym == symb2) ? symb1 : sym;
                DFAState next = original.getNextState(sym);
                if (next != null) {
                    swappedDFA.addTransition(stateName, next.getName(), swappedSym);
                }
            }
        }
        if (startState != null) {
            swappedDFA.setStart(startState.getName());
        }
        for (DFAState finalState : finalStates) {
            swappedDFA.setFinal(finalState.getName());
        }
        return swappedDFA;
    }
}
