import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class test extends JFrame {
    private JLabel[][] label = new JLabel[10][10];
    private JLabel status;
    private final Random random = new Random();
    private int Board_size = 10;

    public test() {
        setTitle("TestFIle");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeBoard();
        setUp();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < Board_size; i++) {
            for (int j = 0; j < Board_size; j++) {
                label[i][j] = new JLabel(Integer.toString(random.nextInt(9) + 1), SwingConstants.CENTER); // JLabel 생성
            }
        }
    }

    private void setUp() {
        JPanel jp = new JPanel(new BorderLayout());

        status = new JLabel("드래그 된 값", SwingConstants.CENTER);
        status.setFont(new Font("Serif", Font.BOLD, 24));
        jp.add(status, BorderLayout.NORTH);

        // Game Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(10, 10));
        jp.add(boardPanel, BorderLayout.CENTER);


        for (int i = 0; i < Board_size; i++) {
            for (int j = 0; j < Board_size; j++) {
                label[i][j].setFont(new Font("Serif", Font.BOLD, 24));
                label[i][j].setHorizontalAlignment(JLabel.CENTER);
                boardPanel.add(label[i][j]);
            }
        }
        jp.addMouseMotionListener(new Mymotion());
        add(jp);
    }

    class Mymotion implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            Component cm = findComponentAt(x, y);
            if (cm instanceof JLabel) {
                JLabel clabel = (JLabel) cm;
                String labelText = clabel.getText();
                status.setText(labelText);
            }
        }

        public void mouseMoved(MouseEvent e) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(test::new);
    }
}
