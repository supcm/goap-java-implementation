package ru.supcm.goap.goals;

import ru.supcm.goap.Target;

public abstract class Goal<T extends Enum<?>> {
    private final T nextState;
    private final T prevState;
    private final int weight;
    private final Target.TargetType targetType;

    /**
     *
     * @param weight weight of the goal. Lesser - more wanted.
     * @param prevState previous or current state of the agent
     * @param nextState wanted state of the agent
     * @param targetType target type (can be Pos or Entity)
     */
    public Goal(int weight, T prevState, T nextState, Target.TargetType targetType) {
        this.nextState = nextState;
        this.prevState = prevState;
        this.weight = weight;
        this.targetType = targetType;
    }

    public int getWeight() {
        return weight;
    }

    public T getNextState() {
        return nextState;
    }

    public Target.TargetType getTargetType() {
        return targetType;
    }

    public T getPrevState() {
        return prevState;
    }
}
