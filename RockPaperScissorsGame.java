import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;

public class RockPaperScissorsGame extends JFrame implements ActionListener {
    public static void main(String[] args) {
        RockPaperScissorsGame game = new RockPaperScissorsGame();
    }

    private JButton rockButton, paperButton, scissorsButton, resetButton;
    private JLabel scoreLabel, userLabel, computerLabel, resultLabel;
    private int userScore, computerScore;
    private Random random;
    private final String[] choices = { "Rock", "Paper", "Scissors" };
    private final Font labelFont = new Font("Arial", Font.BOLD, 16);
    private final Font resultFont = new Font("Arial", Font.BOLD, 24);

    public RockPaperScissorsGame() {
        // set up window
        setTitle("Rock Paper Scissors Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        try {
            JLabel background = new JLabel(new ImageIcon(
                    ImageIO.read(new File("C:/Users/danqu/OneDrive/Desktop/istockphoto-1035676256-612x612.jpg"))));
            background.setBounds(1, 0, 500, 300);
            // create a semi-transparent layer
            JPanel overlay = new JPanel();
            overlay.setBounds(1, 0, 500, 300);
            overlay.setBackground(new Color(0, 0, 0, 128)); // set the background color with alpha value (0-255)
            background.add(overlay, JLayeredPane.PALETTE_LAYER); // add the layer on top of the image
            add(background);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set up buttons
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        resetButton = new JButton("Reset");
        rockButton.setBounds(50, 80, 100, 40);
        paperButton.setBounds(200, 80, 100, 40);
        scissorsButton.setBounds(350, 80, 100, 40);
        resetButton.setBounds(200, 160, 100, 40);
        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        resetButton.addActionListener(this);
        rockButton.setFont(labelFont);
        paperButton.setFont(labelFont);
        scissorsButton.setFont(labelFont);
        resetButton.setFont(labelFont);
        add(rockButton);
        add(paperButton);
        add(scissorsButton);
        add(resetButton);

        // set up score label
        scoreLabel = new JLabel("Score: User - 0, Computer - 0");
        scoreLabel.setBounds(50, 10, 400, 30);
        scoreLabel.setFont(labelFont);
        add(scoreLabel);

        // set up labels for user and computer choices
        userLabel = new JLabel("You picked:");
        computerLabel = new JLabel("Computer picked:");
        userLabel.setBounds(50, 220, 150, 30);
        computerLabel.setBounds(280, 220, 150, 30);
        userLabel.setFont(labelFont);
        computerLabel.setFont(labelFont);
        add(userLabel);
        add(computerLabel);

        // set up label for game result
        resultLabel = new JLabel("");
        resultLabel.setBounds(200, 220, 100, 30);
        resultLabel.setFont(resultFont);
        add(resultLabel);

        // initialize random number generator
        random = new Random();

        // show window
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rockButton || e.getSource() == paperButton || e.getSource() == scissorsButton) {
            String userChoice = e.getActionCommand();
            String computerChoice = generateComputerChoice();
            userLabel.setText("You picked: " + userChoice);
            computerLabel.setText("Computer picked: " + computerChoice);
            String result = compareChoices(userChoice, computerChoice);
            updateScore(result);
            resultLabel.setText(result);
        } else if (e.getSource() == resetButton) {
            resetScore();
            userLabel.setText("You picked:");
            computerLabel.setText("Computer picked:");
            resultLabel.setText("");
        }
    }

    private void resetScore() {
        userScore = 0;
        computerScore = 0;
        scoreLabel.setText("Score: User - 0, Computer - 0");
    }

    private void updateScore(String result) {
        if (result.equals("You Win")) {
            userScore++;
        } else if (result.equals("Computer Wins")) {
            computerScore++;
        }
        scoreLabel.setText("Score: User - " + userScore + ", Computer - " + computerScore);
    }

    private String generateComputerChoice() {
        return choices[random.nextInt(choices.length)];
    }

    private String compareChoices(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "Tie";
        } else if (userChoice.equals("Rock") && computerChoice.equals("Scissors")
                || userChoice.equals("Paper") && computerChoice.equals("Rock")
                || userChoice.equals("Scissors") && computerChoice.equals("Paper")) {
            return "You Win";
        } else {
            return "Computer Wins";
        }
    }
}


