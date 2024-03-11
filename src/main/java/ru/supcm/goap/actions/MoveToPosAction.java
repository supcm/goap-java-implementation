package ru.supcm.goap.actions;

import ru.supcm.Constants;
import ru.supcm.goap.IGoapAgent;
import ru.supcm.goap.Target;
import ru.supcm.level.Enemy;
import ru.supcm.level.Pos;

public class MoveToPosAction extends Action<Enemy.State> {
    public MoveToPosAction() {
        super(0, Enemy.State.CALM, Enemy.State.DISTURBED);
    }

    @Override
    public boolean canPerform(Target target, IGoapAgent<Enemy.State> agent) {
        return target != null && canReach(((Enemy)agent).getPos(), target.getPos());
    }

    @Override
    protected void performPos(Target target, IGoapAgent<Enemy.State> agent) {
        Enemy enemy = (Enemy) agent;
        int x = 0;
        int y = 0;

        if(target.getPos().x() - enemy.getPos().x() < 0)
            x = -1;
        else if(target.getPos().x() - enemy.getPos().x() > 0)
            x = 1;
        if(target.getPos().y() - enemy.getPos().y() < 0)
            y = -1;
        else if(target.getPos().y() - enemy.getPos().y() > 0)
            y = 1;

        enemy.move(x * Constants.ENEMY_SPEED, y * Constants.ENEMY_SPEED);
    }

    @Override
    protected void performEntity(Target target, IGoapAgent<Enemy.State> agent) {
        Enemy enemy = (Enemy) agent;
        int x = 0;
        int y = 0;

        if(target.getPos().x() - enemy.getPos().x() < 0)
            x = -1;
        else if(target.getPos().x() - enemy.getPos().x() > 0)
            x = 1;
        if(target.getPos().y() - enemy.getPos().y() < 0)
            y = -1;
        else if(target.getPos().y() - enemy.getPos().y() > 0)
            y = 1;

        enemy.move(x * Constants.ENEMY_SPEED, y * Constants.ENEMY_SPEED);
    }

    boolean canReach(Pos pos0, Pos pos1) {
        return Math.abs(pos1.x() - pos0.x()) > Constants.ENEMY_SPEED &&
                Math.abs(pos1.y() - pos0.y()) > Constants.ENEMY_SPEED;
    }
}
