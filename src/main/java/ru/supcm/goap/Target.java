package ru.supcm.goap;

import ru.supcm.level.Entity;
import ru.supcm.level.Pos;

public interface Target {
    enum TargetType {
        POS, ENTITY;
    }

    TargetType getTargetType();

    Pos getPos();
    Entity getEntity();

    void update();
}
