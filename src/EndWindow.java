import design.BaseButton;

import javax.swing.*;
import java.awt.*;

public class EndWindow extends JFrame {
    public EndWindow(String winner, String loser) {
        super("Játék vége");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel label = new JLabel(winner + " nyerte a játékot!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 70));
        BaseButton newGame = new BaseButton("Új játék", Color.GREEN, Color.DARK_GRAY);
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        exit.addActionListener(e -> System.exit(0));
        newGame.addActionListener(e -> {
            setVisible(false);
            MainWindow.startGame(loser, winner);
        });
        add(label, BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);
        add(newGame, BorderLayout.NORTH);
        setUndecorated(true);
    }
}
