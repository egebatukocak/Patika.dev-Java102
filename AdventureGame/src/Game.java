import java.util.Scanner;

public class Game {
    private final Scanner scan = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome the Adventure Game !!");
        System.out.print("Enter your name : ");
        String playerName = scan.nextLine();
        Player player = new Player(playerName);
        System.out.println("Welcome " + player.getName() + "!");
        System.out.println("Have fun in the island.");
        player.selectChar();
        Location location = null;
        player.printInfo();
        while (true) {
            if (player.isWin()) {
                System.out.println("YOU WIN !!!!");
                System.out.println("CONGRATULATIONS !!!!");
                break;
            }
            System.out.println();
            System.out.println("------Areas------");
            System.out.println("----Safe Areas---");
            System.out.println("1 - Safe House");
            System.out.println("2 - Tool Store");
            System.out.println("---Danger Areas--");
            System.out.println("3 - Cave");
            System.out.println("4 - Forest");
            System.out.println("5 - River");
            System.out.println("6 - Mine");
            System.out.println("-----------------");
            System.out.println("11 - Player Stats");
            System.out.println("-----------------");
            System.out.println("0 - Exit the Game");
            System.out.println("-----------------");
            System.out.print("Select the location you want to go : ");
            int selectedLocation = scan.nextInt();
            switch (selectedLocation) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                case 11:
                    player.printInfo();
                    continue;
                default:
                    System.out.println();
                    System.out.println("Please select a valid game.location !!");
                    continue;
            }
            if (location == null) {
                System.out.println("Exiting..");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("GAME OVER !!!");
                break;
            }
        }
    }
}
