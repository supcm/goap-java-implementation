package ru.supcm.goap;

import ru.supcm.goap.actions.Action;
import ru.supcm.goap.goals.Goal;

import java.util.*;

public class ActionSelector<T extends Enum<?>> {
    private final Set<Action<T>> actions = new HashSet<>();
    public ActionSelector(Collection<Action<T>> actions) {
        this.actions.addAll(actions);
    }

    /**
     *
     * @param goal wanted goal
     * @param curState current or previous state
     * @param agent agent (AI)
     * @return most preferred (with the minimal weights) action chain
     */
    public Stack<Action<T>> getActionChain(Goal<T> goal, T curState, IGoapAgent<T> agent) {
        T wantedState = goal.getNextState();
        Stack<Action<T>> actionsChain = new Stack<>();
        while(wantedState != curState) {
            List<Action<T>> correctActions = new ArrayList<>();
            for(Action<T> action : actions) {
                if(action.getNextState() == wantedState && action.canPerform(agent.getCurrentTarget(), agent)) {
                    correctActions.add(action);
                }
            }
            if(!correctActions.isEmpty()){
                Action<T> action = getBestAction(correctActions);
                actionsChain.push(action);
                wantedState = action.getPrevState();
            } else
                return new Stack<>();
        }
        Stack<Action<T>> output = new Stack<>();
        while(actionsChain.size() > 0) {
            output.push(actionsChain.pop());
        }
        return output;
    }

    private Action<T> getBestAction(List<Action<T>> actions) {
        Action<T> best = actions.get(0);
        for(Action<T> action : actions) {
            if(action.getWeight() <= best.getWeight())
                best = action;
        }
        return best;
    }
}
