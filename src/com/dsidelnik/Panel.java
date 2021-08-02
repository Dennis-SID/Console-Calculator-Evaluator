package com.dsidelnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private JButton[] numberButtons = new JButton[10];
    private final JButton DOT_BUTTON = new JButton(".");
    private final JButton RESET_BUTTON = new JButton("C");
    private final JButton DIVIDE_BUTTON = new JButton("/");
    private final JButton MULTIPLE_BUTTON = new JButton("X");
    private final JButton PLUS_BUTTON = new JButton("+");
    private final JButton MINUS_BUTTON = new JButton("-");
    private final JButton EQUALS_BUTTON = new JButton("=");
    private final JButton UNARY_BUTTON = new JButton("-/+");
    private final JButton BS_BUTTON = new JButton("<--");
    JTextField textField = new JTextField();
    private final Font FONT = new Font("TimesNewRoman", Font.BOLD, 20);

    private final int BUTTON_HEIGHT = 60;
    private final int BUTTON_WIDTH = 60;
    private final int TEXT_FIELD_HEIGHT = 60;
    private final int TEXT_FIELD_WIDTH = 272;
    private final int indent = 10;

    public Panel() {
        setLayout(null);
        setFocusable(true);
        grabFocus();

        numberButtonsInit();

        setNumberButtons();

        setOperatorButtons();

        setUpperLineOperators();

        setTextField(textField);

        setActionListener();

    }

    private void numberButtonsInit() {
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(i + "");
        }
    }

    private void setButton(JButton button, int buttonX, int buttonY) {
        button.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setFont(FONT);
        add(button);
    }

    private void setNumberButtons() {
        int buttonY = 160;
        int buttonX = 10;
        int buttonNumber = 1;
        for (int x = 0; x < 4; x++) {
            if (x < 3) {
                for (int y = 0; y < 3; y++) {
                    setButton(numberButtons[buttonNumber], buttonX, buttonY);
                    buttonX += BUTTON_WIDTH + indent;
                    buttonNumber++;
                }
                buttonX = indent;
                buttonY += BUTTON_HEIGHT + indent;
            } else {
                setButton(DOT_BUTTON, buttonX, buttonY);
                buttonX += BUTTON_WIDTH + indent;
                setButton(numberButtons[0], buttonX, buttonY);
                buttonX += BUTTON_WIDTH + indent;
                setButton(UNARY_BUTTON, buttonX, buttonY);
            }
        }
    }

    private void setOperatorButtons() {
        int buttonY = 160;
        int buttonX = 220;

        setButton(MINUS_BUTTON, buttonX, buttonY);
        buttonY += BUTTON_HEIGHT + indent;
        setButton(PLUS_BUTTON, buttonX, buttonY);
        buttonY += BUTTON_HEIGHT + indent;
        EQUALS_BUTTON.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT * 2 + indent);
        EQUALS_BUTTON.setFont(FONT);
        add(EQUALS_BUTTON);
    }

    private void setUpperLineOperators() {
        int buttonY = 90;
        int buttonX = 10;

        setButton(RESET_BUTTON, buttonX, buttonY);
        buttonX += BUTTON_WIDTH + indent;
        setButton(DIVIDE_BUTTON, buttonX, buttonY);
        buttonX += BUTTON_WIDTH + indent;
        setButton(MULTIPLE_BUTTON, buttonX, buttonY);
        buttonX += BUTTON_WIDTH + indent;
        setButton(BS_BUTTON, buttonX, buttonY);

    }

    private void setTextField(JTextField textField) {
        textField.setBounds(indent, indent, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textField.setFont(FONT);
        textField.setEditable(false);
        add(textField);
    }

    private void setActionListener() {
        ActionListener listener = (ActionEvent e) -> {
            JButton button = (JButton) e.getSource();
            textField.setText(textField.getText() + button.getText());
        };

        for (JButton button : numberButtons) {
            button.addActionListener(listener);
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();

                if (Character.isDigit(symbol)) {
                    textField.setText(textField.getText() + symbol);
                } else if (symbol == '.' && oneDot()) {
                    textField.setText(textField.getText() + symbol);
                }
            }
        });
    }

    private boolean oneDot() {
        String expression = textField.getText();
        if (expression.length() == 0) return false;

        int index = expression.indexOf('.');
        return index == -1;
    }
}
