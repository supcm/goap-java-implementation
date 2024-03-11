package ru.supcm.level;

import ru.supcm.Constants;
import ru.supcm.KeyHandler;
import ru.supcm.goap.Target;

import java.awt.*;

public class Player extends Entity implements Target {
    public Player(Level level) {
        super(new Pos(100, 100), level);
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.ENTITY;
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }

    @Override
    public Entity getEntity() {
        return this;
    }

    @Override
    public void update() {

    }

    @Override
    public void tick() {
        KeyHandler controller = level.getController();
        int moveX = 0, moveY = 0;
        if(controller.up) {
            if(pos.y() - Constants.PLAYER_SPEED > 0)
                moveY -= Constants.PLAYER_SPEED;
        }
        if(controller.down) {
            if(pos.y() + Constants.PLAYER_SPEED + Constants.TILE_SIZE < Constants.SCREEN_HEIGHT)
                moveY += Constants.PLAYER_SPEED;
        }
        if(controller.left) {
            if(pos.x() - Constants.PLAYER_SPEED > 0)
                moveX -= Constants.PLAYER_SPEED;
        }
        if(controller.right) {
            if(pos.x() + Constants.PLAYER_SPEED + Constants.TILE_SIZE < Constants.SCREEN_WIDTH)
                moveX += Constants.PLAYER_SPEED;
        }
        move(moveX, moveY);
    }
}
