package quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rules extends JFrame implements ActionListener{
    String name;
    JButton start, back;
    rules(String name) {
        this.name = name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Welcome to Quiz Rules");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        
        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
            "<html>"+ 
                "1. Every Quiz is timed 5 minutes." + "<br><br>" +
                "2. There are 5 questions in every quiz." + "<br><br>" +
                "3. There 4 options to each question. Each question carries 1 mark." + "<br><br>" +
                "4. There is no negative marking." + "<br><br>" +
                "5. Each topic has 3 levels of quizzes: Easy, Medium, and Hard. " + "<br><br>" +
                "6. To qualify for a medium quiz: the participant should score a 5/5 in the precedding easy quiz." + "<br><br>" +
                "7. To qualify for a hard quiz: the participant should score 3/5 in the preceeding medium quiz." + "<br><br>" +
                "8. Finally, Good Luck!" + "<br><br>" +
            "<html>"
        );
        
        add(rules);
        start = new JButton("Okay");
        start.setBounds(400, 500, 100, 30);
        start.setBackground(new Color(128, 0, 128));
        start.setForeground(Color.BLUE);
        start.addActionListener(this);
        add(start);
        
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new m();
        } else {
            setVisible(false);
            new logg();
        }
    }
    
    public static void main(String[] args) {
        new rules("User");
    }
}