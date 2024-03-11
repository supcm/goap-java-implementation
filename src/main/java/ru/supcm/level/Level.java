package ru.supcm.level;

import ru.supcm.KeyHandler;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final Player player;
    private final List<Entity> entities = new ArrayList<>();
    private long time = 0;
    private final KeyHandler controller;
    public Level(KeyHandler controller) {
        this.controller = controller;
        player = new Player(this);
        entities.add(player);
        createEnemy();
    }
    public Player getPlayer() {
        return player;
    }
    public List<Entity> getEntities() {
        return entities;
    }

    public long getTime() {
        return time;
    }
    KeyHandler getController() {
        return controller;
    }
    public void createEntity(Entity entity) {
        entities.add(entity);
    }

    public void createEnemy() {
        createEntity(new Enemy(new Pos(150, 150), this));
    }

    public void tick() {
        for(Entity entity : entities)
            entity.tick();
        time++;
    }
}
