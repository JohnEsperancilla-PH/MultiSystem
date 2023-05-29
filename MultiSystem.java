import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MultiSystem {

    private static ArrayList<Account> accounts = new ArrayList<Account>();
    private static Account currentAccount;

    public static void main(String[] args) {
        loadAccountsFromFile();
        while (true) {
            if (currentAccount == null) {
                String[] options = {"Sign-up", "Log-in", "Exit"};
                int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "System Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        signUp();
                        break;
                    case 1:
                        logIn();
                        break;
                    case 2:
                        saveAccountsToFile();
                        System.exit(0);
                        break;
                }
            } else {
                String[] options = {"Calculator", "Notepad", "Cashier", "Log-out", "Exit"};
                int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "System Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        calculator();
                        break;
                    case 1:
                        notepad();
                        break;
                    case 2:
                        cashier();
                        break;
                    case 3:
                        currentAccount = null;
                        break;
                    case 4:
                        saveAccountsToFile();
                        System.exit(0);
                        break;
                }
            }
        }
    }

    private static void signUp() {
        String username = JOptionPane.showInputDialog(null, "Enter a username:");
        String password = JOptionPane.showInputDialog(null, "Enter a password:");
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "Username already exists.");
                return;
            }
        }
        accounts.add(new Account(username, password));
        JOptionPane.showMessageDialog(null, "Account created successfully!");
    }

    private static void logIn() {
        String username = JOptionPane.showInputDialog(null, "Enter your username:");
        String password = JOptionPane.showInputDialog(null, "Enter your password:");
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                currentAccount = account;
                JOptionPane.showMessageDialog(null, "Log-in successful!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid username or password.");
    }

    private static void calculator() {
        double num1 = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the first number:"));
        double num2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the second number:"));
        String[] options = {"Add", "Subtract", "Multiply", "Divide"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an operation:", "Calculator", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        double result = 0;
        switch (choice) {
            case 0:
                result = num1 + num2;
                break;
            case 1:
                result = num1 - num2;
                break;
            case 2:
                result = num1 * num2;
                break;
            case 3:
                result = num1 / num2;
                break;
        }
        JOptionPane.showMessageDialog(null, "Result: " + result);
    }

    private static void notepad() {
        String text = JOptionPane.showInputDialog(null, "Enter some text:");
        JOptionPane.showMessageDialog(null, "You entered:\n" + text);
    }

    private static void cashier() {
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the quantity:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the price:"));
        double total = quantity * price;
        JOptionPane.showMessageDialog(null, "Total: " + total);
    }

    private static void saveAccountsToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"));
            for (Account account : accounts) {
                writer.write(account.getUsername() + "," + account.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadAccountsFromFile() {
        try {
            Scanner scanner = new Scanner(new FileReader("accounts.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                accounts.add(new Account(parts[0], parts[1]));
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
