package MyAgents;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAgent extends Agent {
    private JFrame frame;
    private JTextField num1Field;
    private JTextField num2Field;
    private JComboBox<String> operatorBox;
    private JLabel resultLabel;

    @Override
    protected void setup() {
        System.out.println("UserAgent started.");
        createGUI();
    }

    private void createGUI() {
        // Create the frame
        frame = new JFrame(getLocalName());
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        // Input fields
        frame.add(new JLabel("First Number:"));
        num1Field = new JTextField();
        frame.add(num1Field);

        frame.add(new JLabel("Operator:"));
        operatorBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        frame.add(operatorBox);

        frame.add(new JLabel("Second Number:"));
        num2Field = new JTextField();
        frame.add(num2Field);

        // Submit button
        JButton submitButton = new JButton("Calculate");
        frame.add(submitButton);

        // Result label
        frame.add(new JLabel("Result:"));
        resultLabel = new JLabel("");
        frame.add(resultLabel);

        // Button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(num1Field.getText());
                    double num2 = Double.parseDouble(num2Field.getText());
                    String operator = (String) operatorBox.getSelectedItem();

                    // Send message to CalculAgent
                    ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
                    message.addReceiver(getAID("CalculAgent")); // Ensure CalculAgent is running and named correctly
                    message.setContent(num1 + " " + operator + " " + num2);
                    send(message);

                    // Wait for the result
                    ACLMessage reply = blockingReceive();
                    if (reply != null) {
                        resultLabel.setText(reply.getContent());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
