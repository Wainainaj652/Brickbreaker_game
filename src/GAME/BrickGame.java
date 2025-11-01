package GAME;

import javax.swing.*;

public class BrickGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        GamePlay game = new GamePlay();
        frame.add(game);

        frame.setVisible(true);
        frame.setTitle("Brick Game");
        frame.setResizable(false);
        frame.setBounds(20,20,700,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
