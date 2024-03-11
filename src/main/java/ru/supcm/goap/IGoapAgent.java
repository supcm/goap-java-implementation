package ru.supcm.goap;

import ru.supcm.goap.actions.Action;
import ru.supcm.goap.goals.Goal;

import java.util.Collection;

public interface IGoapAgent<T extends Enum<?>> {
    Target getCurrentTarget();
    void setCurrentTarget(Target target);

    Collection<Action<T>> getActions();
    Collection<Goal<T>> getGoals();
    T getState();
    void setState(T state);

}
