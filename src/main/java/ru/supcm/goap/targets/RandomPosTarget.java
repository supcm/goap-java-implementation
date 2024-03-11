package ru.supcm.goap.targets;

import ru.supcm.Constants;
import ru.supcm.goap.Target;
import ru.supcm.level.Entity;
import ru.supcm.level.Pos;

import java.util.Random;

public class RandomPosTarget implements Target {
    private int x;
    private int y;
    public RandomPosTarget() {
        Random random = new Random();
        this.x = random.nextInt(1, Constants.SCREEN_WIDTH - Constants.TILE_SIZE - 1);
        this.y = random.nextInt(1, Constants.SCREEN_HEIGHT - Constants.TILE_SIZE - 1);
    }
    @Override
    public TargetType getTargetType() {
        return TargetType.POS;
    }

    @Override
    public Pos getPos() {
        return new Pos(x, y);
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void update() {
        Random random = new Random();
        this.x = random.nextInt(1, 400);
        this.y = random.nextInt(1, 400);
    }
}
