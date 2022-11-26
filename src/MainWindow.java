import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("MALOM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("MALOM játék",SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 70));
        JButton load = new JButton("Betöltés");
        JButton newGame = new JButton("Új játék");
        JButton exit = new JButton("Kilépés");
        newGame.setPreferredSize(new Dimension(400, 200));
        load.setPreferredSize(new Dimension(400, 200));
        exit.setPreferredSize(new Dimension(400, 200));
        newGame.setBackground(Color.GREEN);
        newGame.setForeground(Color.DARK_GRAY);
        newGame.setFont(new Font("Arial", Font.PLAIN, 80));
        load.setBackground(Color.YELLOW);
        load.setForeground(Color.DARK_GRAY);
        load.setFont(new Font("Arial", Font.BOLD, 80));
        exit.setBackground(Color.RED);
        exit.setForeground(Color.DARK_GRAY);
        exit.setFont(new Font("Arial", Font.PLAIN, 80));
        exit.addActionListener(e->System.exit(0));
        add(label, BorderLayout.NORTH);
        add(newGame, BorderLayout.CENTER);
        add(load, BorderLayout.WEST);
        add(exit, BorderLayout.EAST);
        setUndecorated(true);
    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        main.setVisible(true);
    }
}
