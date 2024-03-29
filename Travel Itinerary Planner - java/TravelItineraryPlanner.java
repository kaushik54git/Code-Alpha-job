import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TravelItineraryPlanner extends JFrame {
    private JTextField destinationField, startDateField, endDateField, budgetField;
    private JTextArea planTextArea;
    private JButton generateButton;

    public TravelItineraryPlanner() {
        setTitle("Travel Itinerary Planner");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        JLabel destinationLabel = new JLabel("Destination:");
        JLabel startDateLabel = new JLabel("Start Date (MM/DD/YYYY):");
        JLabel endDateLabel = new JLabel("End Date (MM/DD/YYYY):");
        JLabel budgetLabel = new JLabel("Budget ($):");

        destinationField = new JTextField(20);
        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        budgetField = new JTextField(10);

        inputPanel.add(destinationLabel);
        inputPanel.add(destinationField);
        inputPanel.add(startDateLabel);
        inputPanel.add(startDateField);
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateField);
        inputPanel.add(budgetLabel);
        inputPanel.add(budgetField);

        generateButton = new JButton("Generate Itinerary");
        generateButton.addActionListener(new GenerateButtonListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);

        planTextArea = new JTextArea();
        planTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(planTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String destination = destinationField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            double budget = 0;

            try {
                budget = Double.parseDouble(budgetField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TravelItineraryPlanner.this, "Please enter a valid budget.");
                return;
            }

            // Here you can implement the logic to generate the itinerary
            StringBuilder itinerary = new StringBuilder();
            itinerary.append("Travel Itinerary for ").append(destination).append("\n");
            itinerary.append("Start Date: ").append(startDate).append("\n");
            itinerary.append("End Date: ").append(endDate).append("\n");
            itinerary.append("Budget: $").append(budget).append("\n");
            // Add more details like activities, weather information, maps, etc.

            planTextArea.setText(itinerary.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TravelItineraryPlanner::new);
    }
}
