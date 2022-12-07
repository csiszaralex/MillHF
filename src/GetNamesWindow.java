import design.BaseButton;

import javax.swing.*;
import java.awt.*;

public class GetNamesWindow extends JFrame {
    public BaseButton ok;
    public BaseButton cancel;
    JTextField player1Name;
    JTextField player2Name;
    public GetNamesWindow() {
        super("Kérem adja meg a neveket");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel player1 = new JLabel("Játékos 1:");
        JLabel player2 = new JLabel("Játékos 2:");
        player1Name = new JTextField();
        player2Name = new JTextField();
        ok = new BaseButton("OK");
        cancel = new BaseButton("Mégse", Color.RED, Color.DARK_GRAY);

        player1.setFont(new Font("Arial", Font.BOLD, 70));
        player2.setFont(new Font("Arial", Font.BOLD, 70));

        player1Name.setFont(new Font("Arial", Font.PLAIN, 70));
        player2Name.setFont(new Font("Arial", Font.PLAIN, 70));

        setLayout(new GridLayout(3, 2));
        add(player1);
        add(player1Name);
        add(player2);
        add(player2Name);
        add(ok);
        add(cancel);

    }
}
