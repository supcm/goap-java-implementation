package ru.supcm.goap;

import ru.supcm.goap.goals.Goal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GoalSelector<T extends Enum<?>> {
    private final Set<Goal<T>> goals = new HashSet<>();

    /**
     *
     * @param goals collection of Goal. Only these goals will be known by goal selector
     */
    public GoalSelector(Collection<Goal<T>> goals) {
        this.goals.addAll(goals);
    }

    /**
     * Choose best (with minimal weight) goal from all known by goal selector goals
     * @param curState current state of the agent
     * @param target target of the agent
     * @return most wanted goal from all known goals
     */
    public Goal<T> getGoal(T curState, Target target) {
        Goal<T> output = null;
        for(Goal<T> goal : goals) {
            if(output == null) {
                output = goal;
                continue;
            }
            if(goal.getPrevState() == curState && output.getWeight() > goal.getWeight() &&
                    target.getTargetType() == goal.getTargetType())
                output = goal;
        }
        return output;
    }
}
