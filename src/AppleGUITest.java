import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;;

public class AppleGUITest extends JFrame implements ActionListener {

    private static int ContaSizerow = 0;
    private static int ContaSizecol = 0;
    private static int BOARD_SIZE;
    private final int[][] board;
    private static JButton[][] buttons;
    private final Random random = new Random();
    private static JLabel statusLabel;
    private JLabel scoreLabel;
    private static JLabel timerLabel;

    private JButton firstButton = null;
    private int firstX, firstY;
    private int score;

    public AppleGUITest() {
        BOARD_SIZE = lv.BoardSize;
        board = new int[BOARD_SIZE][BOARD_SIZE];
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        ContaSizerow = lv.Containerrow;
        ContaSizecol = lv.Containercol;

        setTitle("Apple Game");
        setSize(ContaSizerow, ContaSizecol);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeBoard();
        setupUI();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = random.nextInt(9) + 1;
            }
        }
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Status Label
        statusLabel = new JLabel("Select the first cell.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Serif", Font.BOLD, 20));
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        // Score Label
        scoreLabel = new JLabel("0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        mainPanel.add(scoreLabel, BorderLayout.SOUTH);

        // Timer Label
        timerLabel = new JLabel("200", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        mainPanel.add(timerLabel, BorderLayout.EAST);

        // Game Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE + 1, BOARD_SIZE + 1));
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        // Add headers
        boardPanel.add(new JLabel("")); // Top-left corner
        for (int i = 0; i < BOARD_SIZE; i++) {
            boardPanel.add(new JLabel(String.valueOf(i), SwingConstants.CENTER));
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            boardPanel.add(new JLabel(String.valueOf((char) ('A' + i)), SwingConstants.CENTER));
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton(String.valueOf(board[i][j]));
                buttons[i][j].setFont(new Font("Serif", Font.BOLD, 24));
                buttons[i][j].addActionListener(this);
                buttons[i][j].setActionCommand(i + "," + j);
                boardPanel.add(buttons[i][j]);
            }
        }
        add(mainPanel);
    }

    // Timer workspace
    public static Timer timer = new Timer();
    public static TimerTask timertask = new TimerTask() {
        public int time = 200;

        public void run() {
                if (time > 0) {
                    timerLabel.setText("" + time);
                    time--;
                } else {
                    timer.cancel();
                    timerLabel.setText("" + time);
                    statusLabel.setText("Timer is over, Game Set.");
                    for (int i = 0; i < BOARD_SIZE; i++) {
                        for (int j = 0; j < BOARD_SIZE; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                }
            }
    };

    public static void timerworking(int a) {
        timer.schedule(timertask, a, 1000);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        String[] parts = command.split(",");
        int y = Integer.parseInt(parts[0]);
        int x = Integer.parseInt(parts[1]);

        JButton clickedButton = buttons[y][x];

        if (firstButton == null) {
            // First selection
            firstButton = clickedButton;
            firstX = x;
            firstY = y;
            firstButton.setBackground(Color.ORANGE);
            statusLabel.setText("Selected (" + x + ", " + (char) ('A' + y) + "). Select the second cell.");
        } else {
            // Second selection
            if (firstButton == clickedButton) {
                // Deselected the same button
                firstButton.setBackground(null);
                firstButton = null;
                statusLabel.setText("Selection cancelled. Select the first cell.");
                return;
            }

            int secondX = x;
            int secondY = y;

            int colstart = Math.min(firstY, secondY);
            int colEnd = Math.max(firstY, secondY);
            int rowStart = Math.min(firstX, secondX);
            int rowEnd = Math.max(firstX, secondX);

            int sum = 0;

            for (int a = colstart; a <= colEnd; a++) {
                for (int b = rowStart; b <= rowEnd; b++) {
                    sum = sum + board[a][b];
                }
            }

            if (sum == 10) {
                // Match found
                for (int a = colstart; a <= colEnd; a++) {
                    for (int b = rowStart; b <= rowEnd; b++) {
                        board[a][b] = 0;
                        score++;
                        buttons[a][b].setText("");
                        buttons[a][b].setEnabled(false);
                        buttons[a][b].setBackground(Color.LIGHT_GRAY);
                    }
                }

                // firstButton.setText("");
                // firstButton.setEnabled(false);
                // firstButton.setBackground(Color.LIGHT_GRAY);

                // clickedButton.setText("");
                // clickedButton.setEnabled(false);
                // clickedButton.setBackground(Color.LIGHT_GRAY);

                String point = Integer.toString(score);

                statusLabel.setText("Success! Cells cleared. Select the first cell.");
                scoreLabel.setText(point);

            } else {
                // No match
                statusLabel.setText("No match. Sum is not 10. Try again.");
                firstButton.setBackground(null); // Reset color
            }

            // Reset selection
            firstButton = null;
        }
    }

    public static void main(String[] args) {
        new lv();
    }
}