package ru.supcm.goap.goals;

import ru.supcm.goap.Target;
import ru.supcm.level.Enemy;

public class FollowPlayerGoal extends Goal<Enemy.State> {
    public FollowPlayerGoal() {
        super(0, Enemy.State.DISTURBED, Enemy.State.CALM, Target.TargetType.ENTITY);
    }
}
