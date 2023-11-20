package quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class quizpg {
    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private JButton backButton;
    private JButton submitButton;
    private int currentQuestion = 0;
    private int score = 0;
    private String selectedTable; // The selected table for the quiz

    private Timer timer;
    private int timeRemaining = 300; // 5 minutes in seconds

    private JLabel timerLabel; // Display the timer

    public quizpg(String selectedQuiz) {
        // Set the selectedTable based on the quiz selection
        if (selectedQuiz != null) {
            if (selectedQuiz.equals("Java (Easy)")) {
                selectedTable = "Java_e";
            } else if (selectedQuiz.equals("Python (Easy)")) {
                selectedTable = "Py_e";
            } else if (selectedQuiz.equals("C/C++ (Easy)")) {
                selectedTable = "Cpp_e";
            }
        }

        frame = new JFrame("Easy Quiz Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(50, 0, 970, 850);

        // Create a main panel to hold everything using GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        frame.setContentPane(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel questionPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(questionPanel, gbc);

        questionLabel = new JLabel("");
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        questionPanel.add(questionLabel, gbc);

        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setForeground(Color.BLACK);
            optionButtons[i].setFont(new Font("Tahoma", Font.PLAIN, 24));
            optionGroup.add(optionButtons[i]);
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            questionPanel.add(optionButtons[i], gbc);
        }

        // Create a panel for buttons and shift them to the bottom right
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(buttonPanel, gbc);

        // Set button size
        Dimension buttonSize = new Dimension(200, 50);

        nextButton = new JButton("Next");
        backButton = new JButton("Back");
        submitButton = new JButton("Submit");

        nextButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);
        submitButton.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        buttonPanel.add(backButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(nextButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        buttonPanel.add(submitButton, gbc);

        nextButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a label for the timer and place it below the Submit button
        JPanel timerPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(timerPanel, gbc);

        // Create a label for the timer
        timerLabel = new JLabel("Time Remaining: " + formatTime(timeRemaining));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        timerLabel.setForeground(Color.BLUE);
        timerPanel.add(timerLabel, gbc);
        // Create a Timer that fires every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                if (timeRemaining <= 0) {
                    timer.stop(); // Stop the timer when time is up
                    submitButton.setEnabled(false); // Disable the "Submit" button
                    JOptionPane.showMessageDialog(frame, "Time's up! Your score: " + score + " out of " + getQuestionCount());
                    System.exit(0);
                }
                timerLabel.setText("Time Remaining: " + formatTime(timeRemaining));}});
        timer.start(); // Start the timer
        // Add action listeners to the buttons for navigation and scoring
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestion < getQuestionCount()) {
                    if (checkAnswer()) {
                        score++;
                    }
                    currentQuestion++;
                    updateQuestion();
                }}});
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestion > 0) {
                    currentQuestion--;
                    updateQuestion();
                }}});

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkAnswer()) {
                    score++;
                }
                JOptionPane.showMessageDialog(frame, "Your score: " + score + " out of " + getQuestionCount());
                if (score == 5) {
                        SwingUtilities.invokeLater(new Runnable() {
                             @Override
                             public void run() {
                                new qqq("Java (Easy)"); // Replace with the selected quiz
                            }});} else {
                                int option = JOptionPane.showOptionDialog(frame,
                    "What would you like to do?",
                    "Quiz Completed",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Restart Quiz", "Quit"},
                    "Restart Quiz");

            if (option == JOptionPane.YES_OPTION) {
                // Restart Quiz
                frame.dispose(); // Close the current frame
                new quizpg("Java (Easy)");}
            else{
                    System.exit(0);}
                
                            }}
        });

        updateQuestion();
        frame.setVisible(true);
    }

    private Connection establishConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "EasyQuiz";
        String username = "root";
        String password = "Nushtweets5678@";

        return DriverManager.getConnection(dbURL + dbName, username, password);
    }

    private int getQuestionCount() {
        return 5;
    }

    private void updateQuestion() {
        String questionText = "";
        String[] options = new String[4];
        if (currentQuestion < getQuestionCount()) {
            try (Connection connection = establishConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT que, a, b, c, d, ans FROM " + selectedTable + " WHERE qno = ?");
                preparedStatement.setInt(1, currentQuestion + 1);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    questionText = resultSet.getString("que");
                    options[0] = resultSet.getString("a");
                    options[1] = resultSet.getString("b");
                    options[2] = resultSet.getString("c");
                    options[3] = resultSet.getString("d");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            questionLabel.setText(questionText);
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setSelected(false);
            }

            backButton.setEnabled(currentQuestion > 0);
            nextButton.setEnabled(currentQuestion < getQuestionCount() - 1);
            submitButton.setEnabled(currentQuestion == getQuestionCount() - 1);
        } else {
            questionLabel.setText("All questions answered.");
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText("");
            }
            nextButton.setEnabled(false);
            submitButton.setEnabled(true);
        }
    }

    private boolean checkAnswer() {
        int selectedOption = -1; // Find which option is selected
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }

        if (selectedOption < 0) {
            return false; // No option selected
        }

        String correctAnswer = "";
        try (Connection connection = establishConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ans FROM " + selectedTable + " WHERE qno = ?");
            preparedStatement.setInt(1, currentQuestion + 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                correctAnswer = resultSet.getString("ans");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return correctAnswer.equals(String.valueOf((char) ('a' + selectedOption)));
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new quizpg("Java (Easy)"); // Replace with the selected quiz
            }});}}