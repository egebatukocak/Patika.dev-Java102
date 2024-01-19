import java.util.Scanner;

public abstract class Location {
    protected Player player;
    protected String name;
    protected int id;
    protected static Scanner scan = new Scanner(System.in);
    public Location(int id, Player player, String name) {
        this.id = id;
        this.player = player;
        this.name = name;
    }
    public abstract boolean onLocation();
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
