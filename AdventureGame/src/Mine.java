import java.util.Random;

public class Mine extends Location {
    private int maxMonsters;
    private String award;
    private Monsters monsters = new Snake();
    private int luck = 50;
    private int randomAwardChance;
    private Weapons weapon;
    private Armors armor;
    public Mine(Player player) {
        super(6, player, "Mine");
        this.maxMonsters = 5;
        this.monsters = new Snake();
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

        System.out.print("Fight or Run : ");
        String selectCase = scan.nextLine().toLowerCase();
        if (selectCase.equals("fight")) {
            System.out.println("Fight");
            if (combat(monsterNum)) {
                System.out.println("All Monsters are Defeated.");
                //ödül yazılcak
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
        } else {
            System.out.println("Running...");
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
                            setRandomDamage();
                            System.out.println(i + ". Snake's Damage : " + this.getMonsters().getDamage());
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
                            setRandomDamage();
                            System.out.println(i + ". Snake's Damage : " + this.getMonsters().getDamage());
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
                } else if (selectCase.equals("run")) {
                    return false;
                } else {
                    System.out.println("Your Choice is invalid !! Please Try Again.");
                }


            }
            ///
            //değis
            //
            //
            if (this.getMonsters().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Enemy Defeated !!");
                randomAwardChance = random();
                if (randomAwardChance <= 45){
                    System.out.println("Enemy doesn't have any award.");
                    System.out.println("You got nothing for winning :( ");
                }else if (randomAwardChance <= 90) {
                    int moneyChance = random();
                    if (moneyChance <= 50){
                        System.out.println("You got 1$ for winning.");
                        this.getMonsters().setAward(1);
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonsters().getAward());
                    }else if (moneyChance <= 80){
                        System.out.println("You got 5$ for winning.");
                        this.getMonsters().setAward(5);
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonsters().getAward());
                    }else {
                        System.out.println("You got 10$ for winning.");
                        this.getMonsters().setAward(10);
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonsters().getAward());
                    }
                    System.out.println("Now you have " + this.getPlayer().getMoney() + "$.");
                } else if (randomAwardChance <= 95) {
                    int weaponChance = random();
                    if (weaponChance <= 50){
                        weapon = new Weapons(1,"Bow and Arrows",2,15);
                        System.out.println("You got " + weapon.getName() + " for winning.");
                        printWeapons(weapon);
                        System.out.println("Do you want to use " + weapon.getName() + "?(yes/no)");
                        String choice = scan.nextLine().toLowerCase();
                        switch (choice){
                            case "yes":
                                this.getPlayer().getInventory().setWeapon(weapon);
                                System.out.println("Weapon  : " + this.getPlayer().getInventory().getWeapon().getName());
                            case "no":
                                System.out.println("Weapon  : " + this.getPlayer().getInventory().getWeapon().getName());
                        }
                    }else if (weaponChance <= 80){
                        weapon = new Weapons(2,"Sword",3,35);
                        System.out.println("You got " + weapon.getName() + " for winning.");
                        printWeapons(weapon);
                        System.out.println("Do you want to use " + weapon.getName() + "?(yes/no)");
                        String choice = scan.nextLine().toLowerCase();
                        switch (choice){
                            case "yes":
                                this.getPlayer().getInventory().setWeapon(weapon);
                                System.out.println("Weapon  : " + this.getPlayer().getInventory().getWeapon().getName());
                            case "no":
                                System.out.println("Weapon  : " + this.getPlayer().getInventory().getWeapon().getName());
                        }
                    }else {
                        weapon = new Weapons(3,"Katana",5,40);
                        System.out.println("You got " + weapon.getName() + " for winning.");
                        printWeapons(weapon);
                        System.out.println("Do you want to use " + weapon.getName() + "?(yes/no)");
                        String choice = scan.nextLine().toLowerCase();
                        switch (choice){
                            case "yes":
                                this.getPlayer().getInventory().setWeapon(weapon);
                                System.out.println("Weapon  : " + this.getPlayer().getInventory().getWeapon().getName());
                            case "no":
                                System.out.println("Weapon  : " + this.getPlayer().getInventory().getWeapon().getName());                        }
                    }
                }else {
                    int armorChance = random();
                    if (armorChance <= 50){
                        armor = new Armors(1,"Light Armor",1,15);
                        System.out.println("You got " + armor.getName() + " for winning.");
                        printArmors(armor);
                        System.out.println("Do you want to use " + armor.getName() + "?(yes/no)");
                        String choice = scan.nextLine().toLowerCase();
                        switch (choice){
                            case "yes":
                                this.getPlayer().getInventory().setArmor(armor);
                                System.out.println("Armor   : " + this.getPlayer().getInventory().getArmor().getName());
                            case "no":
                                System.out.println("Armor   : " + this.getPlayer().getInventory().getArmor().getName());
                        }
                    }else if (armorChance <= 80){
                        armor = new Armors(2,"Medium Armor",3,25);
                        System.out.println("You got " + armor.getName() + " for winning.");
                        printArmors(armor);
                        System.out.println("Do you want to use " + armor.getName() + "?(yes/no)");
                        String choice = scan.nextLine().toLowerCase();
                        switch (choice){
                            case "yes":
                                this.getPlayer().getInventory().setArmor(armor);
                                System.out.println("Armor   : " + this.getPlayer().getInventory().getArmor().getName());
                            case "no":
                                System.out.println("Armor   : " + this.getPlayer().getInventory().getArmor().getName());
                        }
                    }else {
                        armor = new Armors(3,"Heavy Armor",5,40);
                        System.out.println("You got " + armor.getName() + " for winning.");
                        printArmors(armor);
                        System.out.println("Do you want to use " + armor.getName() + "?(yes/no)");
                        String choice = scan.nextLine().toLowerCase();
                        switch (choice){
                            case "yes":
                                this.getPlayer().getInventory().setArmor(armor);
                                System.out.println("Armor   : " + this.getPlayer().getInventory().getArmor().getName());
                            case "no":
                                System.out.println("Armor   : " + this.getPlayer().getInventory().getArmor().getName());
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private void setRandomDamage() {
        int randomDamage;
        if (random() >= 0 && random() <= 25){
            randomDamage = 3;
        }else if(random() > 25 && random() <= 50){
            randomDamage = 4;
        }else if(random() > 50 && random() <= 75){
            randomDamage = 5;
        }else{
            randomDamage = 6;
        }
        this.monsters.setDamage(randomDamage);
    }
    public void setRandomAwardChance() {
        this.randomAwardChance = random();
    }
    public int random(){
        return (int) (Math.random() * 100);
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
        System.out.println("Damage  : 3-6");
        System.out.println("Prize   : Money or Weapon or Armor");
        System.out.println();
    }
    public void printWeapons(Weapons w){
        System.out.println("----Weapon----");
        System.out.println();
        System.out.println((w.getId())+". Weapon : " +w.getName()
                +"\n   Damage : "+w.getDamage()
                +"\n   Price  : "+w.getPrice());
        System.out.println();
    }
    public void printArmors(Armors a){
        System.out.println("----Armor----");
        System.out.println();
        System.out.println((a.getId())+". Armor   : "+a.getName()
                +"\n   Defense : "+a.getDefense()
                +"\n   Price   : "+a.getPrice());
        System.out.println();
    }
    public int randomMonstersNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonsters()) + 1;
    }

    public int getMaxMonsters() {
        return maxMonsters;
    }

    public void setMaxMonsters(int maxMonsters) {
        this.maxMonsters = maxMonsters;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Monsters getMonsters() {
        return monsters;
    }

    public void setMonsters(Monsters monsters) {
        this.monsters = monsters;
    }
}
