package quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class logg {
    private JFrame frame;
    private JTextField usernameField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public logg() {
        frame = new JFrame("Quiz Time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(new Color(128, 0, 128));
        frame.setLayout(null);
    
        // Title label
        JLabel titleLabel = new JLabel("Quiz Time!");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(220, 20, 200, 30);
        frame.add(titleLabel);
    
        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(100, 120, 100, 20); 
        usernameField.setBounds(220, 120, 200, 30); 
        frame.add(usernameLabel);
        frame.add(usernameField);
    
        // Name field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(100, 160, 100, 20);  
        nameField.setBounds(220, 160, 200, 30); 
        frame.add(nameLabel);
        frame.add(nameField);
    
        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(100, 200, 100, 20);  
        passwordField.setBounds(220, 200, 200, 30); 
        frame.add(passwordLabel);
        frame.add(passwordField);
    
        JButton submitButton = new JButton("Submit");
        statusLabel = new JLabel();
        submitButton.setBounds(220, 250, 100, 30);  
    
        frame.add(submitButton);
        frame.add(statusLabel);
    
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String name = nameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (storeData(username, name, password)) {
                    statusLabel.setText("Data stored successfully.");
                    openMainPage();
                } else {
                    statusLabel.setText("Error storing data.");
                }
            }
        });

        frame.setVisible(true);
    }

    private boolean storeData(String username, String name, String password) {
        try {
            // Replace these with your database connection details
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String pass = "Nushtweets5678@";
            String dbname = "login";

            Connection connection = DriverManager.getConnection(url + dbname, user, pass);

            // Create the "users" table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (username varchar(255), name char(50), password varchar(255))";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableSQL);
            }

            String query = "INSERT INTO users (username, name, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);

            int rowsInserted = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void openMainPage() {
        // Open the Main Page
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new m();
                frame.dispose(); // Close the login page after opening the Main Page
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new logg();
            }
        });
    }
}