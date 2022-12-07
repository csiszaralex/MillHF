import design.BaseButton;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("MALOM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("MALOM játék", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 70));
        BaseButton load = new BaseButton("Betöltés", Color.YELLOW, Color.DARK_GRAY);
        BaseButton newGame = new BaseButton("Új játék", Color.GREEN, Color.DARK_GRAY);
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        newGame.addActionListener(e -> newGame());
        exit.addActionListener(e -> System.exit(0));
        load.addActionListener(e -> load());
        add(label, BorderLayout.NORTH);
        add(newGame, BorderLayout.CENTER);
        add(load, BorderLayout.WEST);
        add(exit, BorderLayout.EAST);
        setUndecorated(true);
    }

    public void newGame() {
        setVisible(false);
        GetNamesWindow getNamesWindow = new GetNamesWindow();
        getNamesWindow.setVisible(true);
        getNamesWindow.ok.addActionListener(e -> {
            getNamesWindow.setVisible(false);
            startGame(getNamesWindow.player1Name.getText(), getNamesWindow.player2Name.getText());
        });
        getNamesWindow.cancel.addActionListener(e -> {
            getNamesWindow.setVisible(false);
            setVisible(true);
        });
    }

    public void load() {
        setVisible(false);
        GetFileNameWindow getFileNameWindow = new GetFileNameWindow();
        getFileNameWindow.setVisible(true);
        getFileNameWindow.ok.addActionListener(e -> {
            getFileNameWindow.setVisible(false);
            String file = getFileNameWindow.fileName.getText();
            JPanel center;
            PlayerDetails player1;
            PlayerDetails player2;
            GameWindow gameWindow;
            try {
                FileInputStream f = new FileInputStream(file + ".dat");
                ObjectInputStream in = new ObjectInputStream(f);
                center = (JPanel) in.readObject();
                in.close();
                FileInputStream fp1 = new FileInputStream(file + ".p1.dat");
                ObjectInputStream inp1 = new ObjectInputStream(fp1);
                player1 = (PlayerDetails) inp1.readObject();
                inp1.close();
                FileInputStream fp2 = new FileInputStream(file + ".p2.dat");
                ObjectInputStream inp2 = new ObjectInputStream(fp2);
                player2 = (PlayerDetails) inp2.readObject();
                inp2.close();
                gameWindow = new GameWindow(player1, player2, center);
                gameWindow.setVisible(true);
            } catch (IOException ex) {
                System.out.println("HIBA(IO): " + ex.getMessage());
                newGame();
            } catch (ClassNotFoundException ex) {
                System.out.println("HIBA(CLASS): " + ex.getMessage());
                newGame();
            }
        });
        getFileNameWindow.cancel.addActionListener(e -> {
            getFileNameWindow.setVisible(false);
            setVisible(true);
        });
    }

    public static void startGame(String player1Name, String player2Name) {
        GameWindow gameWindow = new GameWindow(new PlayerDetails(player1Name), new PlayerDetails(player2Name));
        gameWindow.setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        main.setVisible(true);
    }
}
