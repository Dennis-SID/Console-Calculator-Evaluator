package com.dsidelnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JPanel implements ActionListener {
    JTextField textField;


    public static void main (String [] args) {

        createAndShowGUI();

    }

    public CalculatorGUI() {
        super(new GridBagLayout());

        textField = new JTextField(40);
        textField.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);

    }

    public void actionPerformed(ActionEvent event) {
        String text = textField.getText();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.add(new CalculatorGUI());
        frame.setVisible(true);
    }

}

class Test extends JFrame {
    private JButton button = new JButton("Press");
    private JTextField textField = new JTextField("", 10);


    public Test() {
        super("Simple Example");
        this.setBounds(100, 100, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 3, 2, 2));

        button.addActionListener(new ButtonEventListener());
        container.add(button);

    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {

        }
    }
}

