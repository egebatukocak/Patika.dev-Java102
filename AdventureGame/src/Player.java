import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int defaultHealth;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private int winCountCave = 0;
    private int winCountForest = 0;
    private int winCountRiver = 0;
    private boolean win = false;
    private Scanner scan = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        this.inventory =new Inventory();
    }

    public Player(String name, int winCount){
        this(name);
        this.winCountCave = winCount;
    }
    public void selectChar(){
        GameChar[] charList = {new Samurai(),new Archer(),new Knight()};
        System.out.println("Characters");
        System.out.println("--------------------");
        for(GameChar gameChar:charList){
            System.out.println(" Character : " + gameChar.getName()
                    +"\n\tID     : "+gameChar.getId()
                    +"\n\tDamage : "+gameChar.getDamage()
                    +"\n\tHeatlh : "+gameChar.getHealth()
                    +"\n\tMoney  : "+gameChar.getMoney());
            System.out.println("--------------------");
        }
        System.out.print("Choose your Character by ID : ");
        int selectChar = scan.nextInt();
        System.out.println();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
                break;
        }
        if(selectChar==1){
            System.out.println(this.getCharName()+" is your choice.\nYou must love watching anime :) \n");
        }
        if(selectChar==2){
            System.out.println(this.getCharName()+" is your choice.\nGo and get them HawkEye ;) \n");
        }
        if(selectChar==3){
            System.out.println(this.getCharName()+" is your choice.\nYou look so royal and glorious :) \n");
        }

    }
    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setDefaultHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }
    public void printInfo(){
        System.out.println("Your Weapon  : " + this.getInventory().getWeapon().getName()
                +"\nYour Armor   : "+this.getInventory().getArmor().getName()
                +"\nYour Damage  : "+this.getTotalDamage()
                +"\nYour Defence : "+this.getInventory().getArmor().getDefense()
                +"\nYour Health  : "+this.getHealth()
                +"\nYour Money   : "+this.getMoney()
                +"\nCave   Count : "+this.getWinCountCave()
                +"\nForest Count : "+this.getWinCountForest()
                +"\nRiver  Count : "+this.getWinCountRiver());
    }
    public int getTotalDamage(){
        return this.damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage(){
        return this.damage;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCharName() {
        return charName;
    }
    public void setCharName(String charName) {
        this.charName = charName;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public int getDefaultHealth() {
        return defaultHealth;
    }
    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }
    public int getWinCountCave() {
        return winCountCave;
    }

    public void setWinCountCave(int winCount) {
        this.winCountCave = winCount;
    }

    public int getWinCountForest() {
        return winCountForest;
    }

    public void setWinCountForest(int winCountForest) {
        this.winCountForest = winCountForest;
    }

    public int getWinCountRiver() {
        return winCountRiver;
    }

    public void setWinCountRiver(int winCountRiver) {
        this.winCountRiver = winCountRiver;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
