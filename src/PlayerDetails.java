import java.io.Serializable;

public class PlayerDetails implements Serializable {
    public String name;
    static int maxPieces = 9;
    public int piecesOnBoard = 0;
    public int killedPieces = 0;

    public PlayerDetails(String name) {
        this.name = name;
    }
    public PlayerDetails() {
        this.name = "Player";
    }

}
