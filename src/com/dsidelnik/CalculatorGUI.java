package com.dsidelnik;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI {
    public static void main (String [] args) {
        JFrame frame = new JFrame("JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Label("LABEL"), BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(500, 500);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        
    }
}
