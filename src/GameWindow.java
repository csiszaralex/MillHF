import design.BaseButton;
import design.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameWindow extends JFrame {
    GameState game = GameState.PLAYER1PLACE;
    PlayerDetails player1;
    PlayerDetails player2;
    JLabel p1Details = addLabel("");
    JLabel p2Details = addLabel("");
    JLabel allapot;
    JPanel center;
    int moveX;
    int moveY;

    public GameWindow(PlayerDetails p1, PlayerDetails p2, JPanel cent) {
        super("MALOM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        player1 = p1;
        player2 = p2;
        center = cent;

        GridLayout layout = (GridLayout) center.getLayout();
        int rows = layout.getRows();
        int cols = layout.getColumns();
        for (int sor = 0; sor < rows; sor++) {
            for (int oszlop = 0; oszlop < cols; oszlop++) {
                Component c = center.getComponent(sor * cols + oszlop);
                if (c instanceof JPanel) {
                    JPanel panel = (JPanel) c;
                    Component c2 = panel.getComponent(0);
                    if (c2 instanceof RoundButton) {
                        RoundButton button = (RoundButton) c2;
                        int finalSor = sor;
                        int finalOszlop = oszlop;
                        button.addActionListener(e -> playGame(e, finalSor, finalOszlop));
                    }
                }
            }
        }


        JPanel up = new JPanel(new BorderLayout(50, 10));
        BaseButton save = new BaseButton("Mentés");
        allapot = addLabel(getAllapot());
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        save.setPreferredSize(new Dimension(300, 100));
        save.addActionListener(e -> saveGame());
        exit.setPreferredSize(new Dimension(300, 100));
        exit.addActionListener(e -> System.exit(0));
        up.add(save, BorderLayout.WEST);
        up.add(allapot, BorderLayout.CENTER);
        up.add(exit, BorderLayout.EAST);


        add(genPlayerDetails(player1.name, Color.WHITE, 1), BorderLayout.WEST);
        add(genPlayerDetails(player2.name, Color.BLACK, 2), BorderLayout.EAST);
        add(up, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        setUndecorated(true);
    }

    public GameWindow(PlayerDetails p1, PlayerDetails p2) {
        super("MALOM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        player1 = p1;
        player2 = p2;


        JPanel up = new JPanel(new BorderLayout(50, 10));
        BaseButton save = new BaseButton("Mentés");
        allapot = addLabel(getAllapot());
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        save.setPreferredSize(new Dimension(300, 100));
        save.addActionListener(e -> saveGame());
        exit.setPreferredSize(new Dimension(300, 100));
        exit.addActionListener(e -> System.exit(0));
        up.add(save, BorderLayout.WEST);
        up.add(allapot, BorderLayout.CENTER);
        up.add(exit, BorderLayout.EAST);

        State[][] states = {{State.EMPTY, State.H_LINE, State.H_LINE, State.EMPTY, State.H_LINE, State.H_LINE, State.EMPTY},
                {State.V_LINE, State.EMPTY, State.H_LINE, State.EMPTY, State.H_LINE, State.EMPTY, State.V_LINE},
                {State.V_LINE, State.V_LINE, State.EMPTY, State.EMPTY, State.EMPTY, State.V_LINE, State.V_LINE},
                {State.EMPTY, State.EMPTY, State.EMPTY, State.X, State.EMPTY, State.EMPTY, State.EMPTY},
                {State.V_LINE, State.V_LINE, State.EMPTY, State.EMPTY, State.EMPTY, State.V_LINE, State.V_LINE},
                {State.V_LINE, State.EMPTY, State.H_LINE, State.EMPTY, State.H_LINE, State.EMPTY, State.V_LINE},
                {State.EMPTY, State.H_LINE, State.H_LINE, State.EMPTY, State.H_LINE, State.H_LINE, State.EMPTY}
        };

        center = new JPanel(new GridLayout(7, 7, 10, 10));
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                switch (states[i][j]) {
                    case EMPTY:
                        center.add(addBtn(null, i, j));
                        break;
                    case PLAYER1:
                        center.add(addBtn(Color.WHITE, i, j));
                        break;
                    case PLAYER2:
                        center.add(addBtn(Color.BLACK, i, j));
                        break;
                    case V_LINE:
                        center.add(addLabel("|"));
                        break;
                    case H_LINE:
                        center.add(addLabel("—"));
                        break;
                    default:
                        center.add(addLabel(""));
                        break;
                }
            }
        }
        add(genPlayerDetails(player1.name, Color.WHITE, 1), BorderLayout.WEST);
        add(genPlayerDetails(player2.name, Color.BLACK, 2), BorderLayout.EAST);
        add(up, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        setUndecorated(true);
    }


    private void saveGame() {
        GetFileNameWindow getFileNameWindow = new GetFileNameWindow();
        getFileNameWindow.ok.addActionListener(e -> {
            getFileNameWindow.setVisible(false);
            String fileName = getFileNameWindow.fileName.getText();

            try {
                FileOutputStream f = new FileOutputStream(fileName + ".dat");
                ObjectOutputStream out = new ObjectOutputStream(f);
                out.writeObject(center);
                out.close();
                FileOutputStream fp1 = new FileOutputStream(fileName + ".p1.dat");
                ObjectOutputStream outp1 = new ObjectOutputStream(fp1);
                outp1.writeObject(player1);
                outp1.close();
                FileOutputStream fp2 = new FileOutputStream(fileName + ".p2.dat");
                ObjectOutputStream outp2 = new ObjectOutputStream(fp2);
                outp2.writeObject(player2);
                outp2.close();
            } catch (IOException ex) {
                System.out.println("HIBA: " + ex.getMessage());
            }

        });
        getFileNameWindow.cancel.addActionListener(e -> {
            getFileNameWindow.setVisible(false);
            setVisible(true);
        });
    }

    private JPanel genPlayerDetails(String pName, Color color, int player) {
        JPanel bal = new JPanel(new GridLayout(5, 1, 50, 10));
        JLabel playerLabel = new JLabel(pName, SwingConstants.CENTER);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        JPanel sampleP1 = addBtn(color, -1, -1);
        bal.add(new JLabel(""));
        bal.add(playerLabel, BorderLayout.EAST);
        bal.add(sampleP1, BorderLayout.CENTER);
        bal.add(player == 1 ? p1Details : p2Details, BorderLayout.SOUTH);
        bal.setPreferredSize(new Dimension(300, 200));
        return bal;
    }

    private JPanel addBtn(Color color, int i, int j) {
        JPanel panel = new JPanel();
        JButton addBtn = new RoundButton("");
        addBtn.setBackground(color);
        addBtn.addActionListener(e -> playGame(e, i, j));
        panel.add(addBtn);
        return panel;
    }

    private JLabel addLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        return label;
    }

    private void playGame(ActionEvent e, int i, int j) {
        JButton btn = (JButton) e.getSource();
        Color color = btn.getBackground();
        if (game == GameState.PLAYER1PLACE) {
            if (isBlack(color) || isWhite(color)) return;
            btn.setBackground(Color.WHITE);
            player1.piecesOnBoard++;
            game = isInMill(btn.getBackground(), i, j) ? GameState.PLAYER1KILL : GameState.PLAYER2PLACE;
        } else if (game == GameState.PLAYER2PLACE) {
            if (isBlack(color) || isWhite(color)) return;
            btn.setBackground(Color.BLACK);
            player2.piecesOnBoard++;
            if (player2.piecesOnBoard + player2.killedPieces >= PlayerDetails.maxPieces) {
                game = isInMill(btn.getBackground(), i, j) ? GameState.PLAYER2KILL : GameState.PLAYER1MOVE;
            } else {
                game = isInMill(btn.getBackground(), i, j) ? GameState.PLAYER2KILL : GameState.PLAYER1PLACE;
            }
            isInMill(color, i, j);
        } else if (game == GameState.PLAYER1KILL) {
            if (isBlack(color)) {
                if (isInMill(btn.getBackground(), i, j)) return;
                btn.setBackground(null);
                player2.killedPieces++;
                player2.piecesOnBoard--;
                game = player2.piecesOnBoard + player2.killedPieces == PlayerDetails.maxPieces ? GameState.PLAYER2MOVE : GameState.PLAYER2PLACE;
                if (player2.piecesOnBoard < 3 && player2.killedPieces > 6) {
                    game = GameState.PLAYER1WIN;
                    endGame();
                }

            }
        } else if (game == GameState.PLAYER2KILL) {
            if (isWhite(color)) {
                if (isInMill(btn.getBackground(), i, j)) return;
                btn.setBackground(null);
                player1.killedPieces++;
                player1.piecesOnBoard--;
                game = player1.piecesOnBoard + player1.killedPieces == PlayerDetails.maxPieces ? GameState.PLAYER1MOVE : GameState.PLAYER1PLACE;
                if (player1.piecesOnBoard < 3 && player1.killedPieces > 6) {
                    game = GameState.PLAYER2WIN;
                    endGame();
                }

            }
        } else if (game == GameState.PLAYER1MOVE) {
            if (isWhite(color)) {
                moveX = i;
                moveY = j;
                btn.setBackground(Color.GREEN);
                game = GameState.PLAYER1MOVEPROGRESS;
            }
        } else if (game == GameState.PLAYER1MOVEPROGRESS) {
            if (j == moveY && i == moveX) {
                btn.setBackground(Color.WHITE);
                game = GameState.PLAYER1MOVE;
            } else if (!isWhite(color) && !isBlack(color) && (isNeighbour(i, j, moveX, moveY) || player1.piecesOnBoard <= 3)) {
                btn.setBackground(Color.WHITE);
                JPanel panel = (JPanel) center.getComponent(moveX * 7 + moveY);
                JButton button = (JButton) panel.getComponent(0);
                button.setBackground(null);
                game = isInMill(btn.getBackground(), i, j) ? GameState.PLAYER1KILL : GameState.PLAYER2MOVE;
            }
        } else if (game == GameState.PLAYER2MOVE) {
            if (isBlack(color)) {
                moveX = i;
                moveY = j;
                btn.setBackground(Color.GREEN);
                game = GameState.PLAYER2MOVEPROGRESS;
            }
        } else if (game == GameState.PLAYER2MOVEPROGRESS) {
            if (j == moveY && i == moveX) {
                btn.setBackground(Color.BLACK);
                game = GameState.PLAYER2MOVE;
            } else if (!isBlack(color) && !isWhite(color) && (isNeighbour(i, j, moveX, moveY)) || player2.piecesOnBoard <= 3) {
                btn.setBackground(Color.BLACK);
                JPanel panel = (JPanel) center.getComponent(moveX * 7 + moveY);
                JButton button = (JButton) panel.getComponent(0);
                button.setBackground(null);
                game = isInMill(btn.getBackground(), i, j) ? GameState.PLAYER2KILL : GameState.PLAYER1MOVE;
            }

        }


        allapot.setText(getAllapot());
        if (player1.piecesOnBoard + player1.killedPieces < PlayerDetails.maxPieces && player2.piecesOnBoard + player2.killedPieces < PlayerDetails.maxPieces) {
            p1Details.setText("Még: " + (PlayerDetails.maxPieces - player1.piecesOnBoard - player1.killedPieces));
            p2Details.setText("Még: " + (PlayerDetails.maxPieces - player2.piecesOnBoard - player2.killedPieces));
        } else {
            p1Details.setText("Maradt: " + player1.piecesOnBoard);
            p2Details.setText("Maradt: " + player2.piecesOnBoard);
        }


    }

    public static boolean isWhite(Color color) {
        return color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255;
    }

    public static boolean isBlack(Color color) {
        return color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0;
    }

    private boolean isInMill(Color background, int i, int j) {
        GridLayout layout = (GridLayout) center.getLayout();
        int rows = layout.getRows();
        int cols = layout.getColumns();
        int btns = 0;
        int sameBtns = 0;
        //oszlop-e
        for (int sor = 0; sor < rows; sor++) {
            int index = sor * cols + j;
            Component panel = center.getComponent(index);
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                Component c = p.getComponent(0);
                if (c instanceof JButton) {
                    btns++;
                    if (btns == 4) {
                        btns = 1;
                        sameBtns = 0;
                    }
                    if ((isBlack(c.getBackground()) && isBlack(background)) || isWhite(c.getBackground()) && isWhite(background)) {
                        sameBtns++;
                        if (sameBtns == 3) {
                            return true;
                        }
                    }
                }
            }
        }
        btns = 0;
        sameBtns = 0;
        //sor-e
        for (int oszlop = 0; oszlop < cols; oszlop++) {
            int index = i * cols + oszlop;
            Component panel = center.getComponent(index);
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                Component c = p.getComponent(0);
                if (c instanceof JButton) {
                    btns++;
                    if (btns == 4) {
                        btns = 1;
                        sameBtns = 0;
                    }
                    if ((isBlack(c.getBackground()) && isBlack(background)) || isWhite(c.getBackground()) && isWhite(background)) {
                        sameBtns++;
                        if (sameBtns == 3) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isNeighbour(int i, int j, int i2, int j2) {
        if (i != i2 && j != j2) return false;
        int iMin = Math.min(i, i2);
        int iMax = Math.max(i, i2);
        int jMin = Math.min(j, j2);
        int jMax = Math.max(j, j2);
        GridLayout layout = (GridLayout) center.getLayout();
        int rows = layout.getRows();
        int cols = layout.getColumns();
        //SameColumn
        if (j == j2) {
            for (int sor = iMin; sor < iMax; sor++) {
                int index = sor * cols + j;
                Component panel = center.getComponent(index);
                if (panel instanceof JPanel) {
                    JPanel p = (JPanel) panel;
                    Component c = p.getComponent(0);
                    if (c instanceof JButton && sor != i && sor != i2) {
                        return false;
                    }
                }
            }
        }
        //SameRow
        if (i == i2) {
            for (int oszlop = jMin; oszlop < jMax; oszlop++) {
                int index = i * cols + oszlop;
                Component panel = center.getComponent(index);
                if (panel instanceof JPanel) {
                    JPanel p = (JPanel) panel;
                    Component c = p.getComponent(0);
                    if (c instanceof JButton && oszlop != j && oszlop != j2) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private String getAllapot() {
        switch (game) {
            case PLAYER1PLACE:
                return "Első játékos helyez bábut";
            case PLAYER2PLACE:
                return "Második játékos helyez bábut";
            case PLAYER1MOVE:
                return "Első játékos mozgat";
            case PLAYER2MOVE:
                return "Második játékos mozgat";
            case PLAYER1KILL:
                return "Első játékos vesz le";
            case PLAYER2KILL:
                return "Második játékos vesz le";
            case PLAYER1MOVEPROGRESS:
                return "Első játékos mozgat, de hova?";
            case PLAYER2MOVEPROGRESS:
                return "Második játékos mozgat, de hova?";
            default:
                return "VÉGE";
        }
    }

    private void endGame() {
        EndWindow endWindow;
        if (game == GameState.PLAYER1WIN) {
            endWindow = new EndWindow(player1.name, player2.name);
        } else {
            endWindow = new EndWindow(player2.name, player1.name);
        }
        endWindow.setVisible(true);
    }
}
