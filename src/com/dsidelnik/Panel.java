package com.dsidelnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private JButton[] numberButtons = new JButton[10];
    private final JButton dotButton = new JButton(".");
    private final JButton resetButton = new JButton("C");
    private final JButton divideButton = new JButton("/");
    private final JButton multipleButton = new JButton("X");
    private final JButton plusButton = new JButton("+");
    private final JButton minusButton = new JButton("-");
    private final JButton equalsButton = new JButton("=");
    private final JButton unaryButton = new JButton("-/+");
    private final JButton backSpaceButton = new JButton("<--");
    JTextField textField = new JTextField();
    private final Font font = new Font("TimesNewRoman", Font.BOLD, 20);

    private final int buttonHeight = 60;
    private final int buttonWidth = 60;
    private final int textFieldHeight = 60;
    private final int textFieldWidth = 272;
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
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        button.setFont(font);
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
                    buttonX += buttonWidth + indent;
                    buttonNumber++;
                }
                buttonX = indent;
                buttonY += buttonHeight + indent;
            } else {
                setButton(dotButton, buttonX, buttonY);
                buttonX += buttonWidth + indent;
                setButton(numberButtons[0], buttonX, buttonY);
                buttonX += buttonWidth + indent;
                setButton(unaryButton, buttonX, buttonY);
            }
        }
    }

    private void setOperatorButtons() {
        int buttonY = 160;
        int buttonX = 220;

        setButton(minusButton, buttonX, buttonY);
        buttonY += buttonHeight + indent;
        setButton(plusButton, buttonX, buttonY);
        buttonY += buttonHeight + indent;
        equalsButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight * 2 + indent);
        equalsButton.setFont(font);
        add(equalsButton);
    }

    private void setUpperLineOperators() {
        int buttonY = 90;
        int buttonX = 10;

        setButton(resetButton, buttonX, buttonY);
        buttonX += buttonWidth + indent;
        setButton(divideButton, buttonX, buttonY);
        buttonX += buttonWidth + indent;
        setButton(multipleButton, buttonX, buttonY);
        buttonX += buttonWidth + indent;
        setButton(backSpaceButton, buttonX, buttonY);

    }

    private void setTextField(JTextField textField) {
        textField.setBounds(indent, indent, textFieldWidth, textFieldHeight);
        textField.setFont(font);
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

                if (!Character.isDigit(symbol)) return;

                textField.setText(textField.getText() + symbol);
            }
        });
    }
}
