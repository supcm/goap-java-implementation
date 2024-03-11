package ru.supcm.level;

import java.awt.*;

public class Entity {

    protected Pos pos;
    protected Level level;
    public Entity(Level level) {
        this(new Pos(0, 0), level);
    }
    public Entity(Pos pos, Level level) {
        this.pos = pos;
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public Pos getPos() {
        return pos;
    }

    public void move(int x, int y) {
        pos.add(x, y);
    }

    public void tick() {}

    public Color getColor() {
        return Color.WHITE;
    };
}
