package ru.supcm.goap.goals;

import ru.supcm.goap.Target;
import ru.supcm.level.Enemy;

public class WalkAroundGoal extends Goal<Enemy.State> {
    public WalkAroundGoal() {
        super(1, Enemy.State.CALM, Enemy.State.DISTURBED, Target.TargetType.POS);
    }
}
