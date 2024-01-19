import java.util.Random;

public abstract class BattleLocation extends Location {
    private Monsters monsters;
    private String award;
    private int maxMonsters;
    private int luck = 50;

    public BattleLocation(int id, Player player, String name, Monsters monsters, String award, int maxMonsters) {
        super(id, player, name);
        this.monsters = monsters;
        this.award = award;
        this.maxMonsters = maxMonsters;
    }

    @Override
    public boolean onLocation() {
        int monsterNum = this.randomMonstersNumber();
        System.out.println("Your Location : " + this.getName());
        if (monsterNum == 1) {
            System.out.println("WARNING !!! The Area Have a " + this.getMonsters().getName() + ".");
        } else {
            System.out.println("WARNING !!! The Area Have " + monsterNum + " " + this.getMonsters().getName() + "s.");
        }
        System.out.println("If You Kill All Monsters 5 times, You Win the " + this.award + ".");
        switch (this.getName()){
            case "Cave":
                System.out.println("Cave Count : " + this.getPlayer().getWinCountCave());
                break;
            case "Forest":
                System.out.println("Forest Count : " + this.getPlayer().getWinCountForest());
                break;
            case "River":
                System.out.println("River Count : " + this.getPlayer().getWinCountRiver());
                break;
        }
        System.out.print("Fight or Run : ");
        String selectCase = scan.nextLine().toLowerCase();
        if (selectCase.equals("fight")) {
            System.out.println("Fight");
            if (combat(monsterNum)) {
                System.out.println("All Monsters are Defeated.");
                switch (this.getName()){
                    case "Cave":
                        this.getPlayer().setWinCountCave(this.getPlayer().getWinCountCave() + 1);
                        System.out.println("Cave Count : " + this.getPlayer().getWinCountCave());
                        if(this.getPlayer().getWinCountCave() == 5) {
                            this.getPlayer().getInventory().setFood(true);
                            System.out.println("Congratulations !!!");
                            System.out.println("You Have the " + this.getAward() + ".");
                        }
                        break;
                    case "Forest":
                        this.getPlayer().setWinCountForest(this.getPlayer().getWinCountForest() + 1);
                        System.out.println("Forest Count : " + this.getPlayer().getWinCountForest());
                        if(this.getPlayer().getWinCountForest() == 5) {
                            this.getPlayer().getInventory().setFirewood(true);
                            System.out.println("Congratulations !!!");
                            System.out.println("You Have the " + this.getAward() + ".");
                        }
                        break;
                    case "River":
                        this.getPlayer().setWinCountRiver(this.getPlayer().getWinCountRiver() + 1);
                        System.out.println("River Count : " + this.getPlayer().getWinCountRiver());
                        if(this.getPlayer().getWinCountRiver() == 5){
                            this.getPlayer().getInventory().setWater(true);
                            System.out.println("Congratulations !!!");
                            System.out.println("You Have the " + this.getAward() + ".");
                        }
                        break;
                }
                return true;
            }
        } else if (selectCase.equals("run")) {
            System.out.println("Running...");
        } else {
            System.out.println("Your Choice is invalid !! Please Try Again.");
            System.out.print("Fight or Run : ");
            selectCase = scan.nextLine().toLowerCase();
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You're Dead !!!");
            return false;
        }
        return true;
    }

    public boolean combat(int monsterNum) {
        for (int i = 1; i <= monsterNum; i++) {
            this.getMonsters().setHealth(this.getMonsters().getDefHealth());
            playerStats();
            monstersStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonsters().getHealth() > 0) {
                System.out.print("Hit or Run : ");
                String selectCase = scan.nextLine().toLowerCase();
                if (selectCase.equals("hit")) {
                    if (firstHit()) {
                        System.out.println("You Hit the " + this.getMonsters().getName() + " !!");
                        this.getMonsters().setHealth(this.getMonsters().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getMonsters().getHealth() > 0) {
                            System.out.println(this.getMonsters().getName() + " Strike Back !!");
                            int monstersTotalDamage = this.getMonsters().getDamage() - this.getPlayer().getInventory().getArmor().getDefense();
                            if (monstersTotalDamage < 0) {
                                monstersTotalDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monstersTotalDamage);
                            afterHit();
                        }
                    } else {
                        if (this.getMonsters().getHealth() > 0) {
                            System.out.println(this.getMonsters().getName() + " Hits You First !!");
                            int monstersTotalDamage = this.getMonsters().getDamage() - this.getPlayer().getInventory().getArmor().getDefense();
                            if (monstersTotalDamage < 0) {
                                monstersTotalDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monstersTotalDamage);
                            afterHit();
                        }
                        System.out.println("You Strike Back !!");
                        this.getMonsters().setHealth(this.getMonsters().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                } else {
                    return false;
                }


            }
            if (this.getMonsters().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Enemy Defeated !!");
                System.out.println("You got " + this.getMonsters().getAward() + "$ for winning.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonsters().getAward());
                System.out.println("Now you have " + this.getPlayer().getMoney() + "$.");
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean firstHit() {
        double random = Math.random() * 100;
        return random <= this.luck;
    }

    public void afterHit() {
        System.out.println("Your Health    : " + this.getPlayer().getHealth());
        System.out.println(this.getMonsters().getName() + "'s Health : " + this.getMonsters().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("--Player Stats--");
        System.out.println("Health  : " + this.getPlayer().getHealth());
        System.out.println("Damage  : " + this.getPlayer().getTotalDamage());
        System.out.println("Weapon  : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Armor   : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Defence : " + this.getPlayer().getInventory().getArmor().getDefense());
        System.out.println("Money   : " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void monstersStats(int i) {
        System.out.println("--" + i + ". " + this.getMonsters().getName() + " Stats--");
        System.out.println("Health  : " + getMonsters().getHealth());
        System.out.println("Damage  : " + getMonsters().getDamage());
        System.out.println("Prize   : " + getMonsters().getAward());
        System.out.println();
    }

    public int randomMonstersNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonsters()) + 1;
    }

    public Monsters getMonsters() {
        return monsters;
    }

    public void setMonsters(Monsters monsters) {
        this.monsters = monsters;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonsters() {
        return maxMonsters;
    }

    public void setMaxMonsters(int maxMonsters) {
        this.maxMonsters = maxMonsters;
    }
}
