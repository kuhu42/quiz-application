package quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class m {
    private JFrame frame;
    private JComboBox<String> quizSelector;

    public m() {
        frame = new JFrame("Quiz Time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(null);

        frame.getContentPane().setBackground(new Color(128, 0, 128));

        JLabel titleLabel = new JLabel("Quiz Time!");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(350, 100, 400, 50);
        frame.add(titleLabel);

        String[] quizOptions = {"Java (Easy)", "Python (Easy)", "C/C++ (Easy)"};
        quizSelector = new JComboBox<>(quizOptions);
        quizSelector.setBounds(400, 200, 200, 30);
        frame.add(quizSelector);

        JButton rulesButton = new JButton("Rules");
        rulesButton.setBounds(400, 300, 200, 40);
        rulesButton.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(rulesButton);

        JButton startQuizButton = new JButton("Start Quiz");
        startQuizButton.setBounds(400, 400, 200, 40);
        startQuizButton.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(startQuizButton);

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rules rulesPage = new rules(null);
                rulesPage.setVisible(true);
            } });

        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedQuiz = (String) quizSelector.getSelectedItem();

                if (selectedQuiz != null) {
                    frame.setVisible(false);

                    if (selectedQuiz.equals("Java (Easy)")) {
                        new quizpg("Java (Easy)");
                    } else if (selectedQuiz.equals("Python (Easy)")) {
                        new quizpg("Python (Easy)");
                    } else if (selectedQuiz.equals("C/C++ (Easy)")) {
                        new quizpg("C/C++ (Easy)");
                    }
                }
            }
        });

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new m();
            }
        });}}