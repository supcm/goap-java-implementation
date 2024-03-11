package ru.supcm;

import ru.supcm.goap.IGoapAgent;
import ru.supcm.level.Entity;
import ru.supcm.level.Level;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    private Thread game;
    private final KeyHandler controller = new KeyHandler();
    Level level;
    public Game() {
        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(controller);
        setFocusable(true);
        level = new Level(controller);
        game = new Thread(this);
        game.start();
    }

    @Override public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D render = (Graphics2D) graphics;

        for(Entity entity : level.getEntities()) {
            if(entity instanceof IGoapAgent<?> agent) {
                render.setColor(Color.WHITE);
                render.drawString(agent.getCurrentTarget().toString(), entity.getPos().x(), entity.getPos().y() - 10);
                render.setColor(Color.BLACK);
            }
            render.setColor(entity.getColor());
            render.fillRect(entity.getPos().x(), entity.getPos().y(), Constants.TILE_SIZE, Constants.TILE_SIZE);
            render.setColor(Color.BLACK);
        }

        render.dispose();
    }

    public void closeGame() {
        game.stop();
    }

    public KeyHandler getController() {
        return controller;
    }

    @Override public void run() {
        double delta = 0;
        double drawInterval = (double) 1000000000 /60;
        long lastTime = System.nanoTime();
        long curTime;

        while (game != null) {
            curTime = System.nanoTime();

            delta += (curTime - lastTime) / drawInterval;
            lastTime = curTime;

            if (delta >= 1) {
                tick();
                repaint();
                delta--;
            }
        }
    }

    public void tick() {
        level.tick();
    }
}
