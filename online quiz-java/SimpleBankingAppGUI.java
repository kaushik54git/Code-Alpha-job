import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleBankingAppGUI extends JFrame {
    private double balance = 0;

    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton depositButton, withdrawButton, checkBalanceButton, exitButton;

    public SimpleBankingAppGUI() {
        setTitle("Simple Banking Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        balanceLabel = new JLabel("Balance: $" + balance);
        amountField = new JTextField(10);
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");
        exitButton = new JButton("Exit");

        depositButton.addActionListener(new DepositButtonListener());
        withdrawButton.addActionListener(new WithdrawButtonListener());
        checkBalanceButton.addActionListener(new CheckBalanceButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        add(balanceLabel);
        add(new JLabel("Amount: "));
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);
        add(exitButton);

        setVisible(true);
    }

    private void deposit(double amount) {
        balance += amount;
        updateBalanceLabel();
    }

    private void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            updateBalanceLabel();
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds!");
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(this, "Current Balance: $" + balance);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    deposit(amount);
                } else {
                    JOptionPane.showMessageDialog(SimpleBankingAppGUI.this, "Please enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(SimpleBankingAppGUI.this, "Please enter a valid number.");
            }
            amountField.setText("");
        }
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    withdraw(amount);
                } else {
                    JOptionPane.showMessageDialog(SimpleBankingAppGUI.this, "Please enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(SimpleBankingAppGUI.this, "Please enter a valid number.");
            }
            amountField.setText("");
        }
    }

    private class CheckBalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkBalance();
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(SimpleBankingAppGUI.this,
                    "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleBankingAppGUI::new);
    }
}
