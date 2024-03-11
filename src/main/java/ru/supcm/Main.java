package ru.supcm;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        setWindow();
    }

    private static void setWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GOAP game test");
        frame.setResizable(false);
        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}