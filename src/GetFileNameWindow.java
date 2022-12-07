import design.BaseButton;

import javax.swing.*;
import java.awt.*;

public class GetFileNameWindow extends JFrame {
    public BaseButton ok;
    public BaseButton cancel;
    JTextField fileName;

    public GetFileNameWindow() {
        super("Kérem adja meg a file nevét");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel label = new JLabel("Fájl neve:");
        fileName = new JTextField();
        ok = new BaseButton("OK");
        cancel = new BaseButton("Mégse", Color.RED, Color.DARK_GRAY);

        label.setFont(new Font("Arial", Font.BOLD, 70));

        fileName.setFont(new Font("Arial", Font.PLAIN, 70));

        setLayout(new GridLayout(2, 2));
        add(label);
        add(fileName);
        add(ok);
        add(cancel);

    }
}
