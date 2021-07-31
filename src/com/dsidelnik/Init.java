package com.dsidelnik;

import javax.swing.*;

public class Init {

    private JFrame frame;

    public Init() {
        frame = new JFrame("Simple calculator");
        frame.setSize(310, 490);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Panel());
        frame.setVisible(true);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Init();
            }
        });
    }

}
