package com.mycompany.chatapp;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ChatApp extends JFrame {
    private final JTextArea chatArea;
    private final JTextField inputField;
    private final JButton sendButton;
    private static String currentUsername;

    private final List<String> messageHistory = new ArrayList<>();

    // Replace database with this in-memory store
    private static final Map<String, User> users = new HashMap<>();

    // User class for in-memory data
    static class User {
        String name, surname, idNumber, phone, username, password;

        User(String name, String surname, String idNumber, String phone, String username, String password) {
            this.name = name;
            this.surname = surname;
            this.idNumber = idNumber;
            this.phone = phone;
            this.username = username;
            this.password = password;
        }
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("0\\d{9}");
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.length() <= 12 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    public static boolean saveUserToMemory(String name, String surname, String idNumber, String phone, String username, String password) {
        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists.");
            return false;
        }

        users.put(username, new User(name, surname, idNumber, phone, username, password));
        return true;
    }

    public ChatApp(String username) {
        currentUsername = username;

        setTitle("Chat - " + username);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");
        JButton searchButton = new JButton("Search Messages");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(searchButton, BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
        searchButton.addActionListener(e -> searchMessages());

        setVisible(true);
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            String time = new SimpleDateFormat("HH:mm").format(new Date());
            String fullMessage = currentUsername + ": " + message + " [" + time + "] ✔ Sent\n";
            chatArea.append(fullMessage);
            messageHistory.add(fullMessage);
            inputField.setText("");

            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    chatArea.append("    ✔ Delivered\n");
                    messageHistory.add("    ✔ Delivered\n");
                    Thread.sleep(1500);
                    chatArea.append("    ✔✔ Read\n");
                    messageHistory.add("    ✔✔ Read\n");
                } catch (InterruptedException ignored) {}
            }).start();
        }
    }

    private void searchMessages() {
        String keyword = JOptionPane.showInputDialog(this, "Enter a word or phrase to search:");
        if (keyword != null && !keyword.isBlank()) {
            StringBuilder results = new StringBuilder("Search Results:\n");
            for (String msg : messageHistory) {
                if (msg.toLowerCase().contains(keyword.toLowerCase())) {
                    results.append(msg).append("\n");
                }
            }

            if (results.toString().equals("Search Results:\n")) {
                JOptionPane.showMessageDialog(this, "No messages found containing: " + keyword);
            } else {
                JTextArea textArea = new JTextArea(results.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(350, 200));
                JOptionPane.showMessageDialog(this, scrollPane, "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void showRegistrationForm() {
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Surname:"));
        panel.add(surnameField);
        panel.add(new JLabel("ID Number (13 digits):"));
        panel.add(idField);
        panel.add(new JLabel("Phone Number (10 digits, starts with 0):"));
        panel.add(phoneField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password (8-12 chars, 1 uppercase, 1 digit, 1 special char):"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null, panel, "User Registration", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String id = idField.getText().trim();
            String phone = phoneField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (!id.matches("\\d{13}")) {
                JOptionPane.showMessageDialog(null, "ID number must be 13 digits.");
                return;
            }

            if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(null, "Invalid phone number.");
                return;
            }

            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Invalid password.");
                return;
            }

            if (saveUserToMemory(name, surname, id, phone, username, password)) {
                JOptionPane.showMessageDialog(null, "Registration successful!");
                SwingUtilities.invokeLater(() -> new ChatApp(username));
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatApp::showRegistrationForm);
    }
}
