package ru.supcm.goap.actions;

import ru.supcm.goap.IGoapAgent;
import ru.supcm.goap.Target;

public abstract class Action<T extends Enum<?>> {
    private final T nextState;
    private final T prevState;
    private final int weight;

    /**
     *
     * @param weight weight of the action. Lesser - more wanted.
     * @param prevState previous or current state
     * @param nextState state at the end of the action
     */
    public Action(int weight, T prevState, T nextState) {
        this.nextState = nextState;
        this.prevState = prevState;
        this.weight = weight;
    }

    public T getNextState() {
        return nextState;
    }

    public T getPrevState() {
        return prevState;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Checks if action can be activated
     * @param target target (maybe not equal to agent's one)
     * @param agent agent (AI)
     * @return true - can be activated, false - can't be activated
     */
    public abstract boolean canPerform(Target target, IGoapAgent<T> agent);

    /**
     * Activates the action if the target is Pos
     * @param target target (maybe not equal to agent's one)
     * @param agent agent (AI)
     */
    protected abstract void performPos(Target target, IGoapAgent<T> agent);

    /**
     * Activates the action if the target is Entity
     * @param target target (maybe not equal to agent's one)
     * @param agent agent (AI)
     */
    protected abstract void performEntity(Target target, IGoapAgent<T> agent);

    /**
     * Activates the action
     * @param target target (maybe not equal to agent's one)
     * @param agent agent (AI)
     */
    public void performAction(Target target, IGoapAgent<T> agent) {
        if(target.getTargetType() == Target.TargetType.POS)
            performPos(target, agent);
        else
            performEntity(target, agent);
        agent.setState(nextState);
    };
}
