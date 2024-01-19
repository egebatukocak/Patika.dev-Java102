public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(2,player, "Tool Store");
    }
    @Override
    public boolean onLocation(){
        System.out.println();
        System.out.println("Welcome the Tool Store !");
        System.out.println();
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Weapons");
            System.out.println("2 - Armors");
            System.out.println("\n0 - Exit the store");
            System.out.print("Your Choice : ");
            int selectCase = scan.nextInt();
            String s = scan.nextLine();
            System.out.println();
            while (selectCase<0 || selectCase>3){
                System.out.println("Your Choice is invalid. Choice Again : ");
                selectCase = scan.nextInt();
            }
            switch (selectCase){
                case 0:
                    System.out.println("See you later :)");
                    System.out.println();
                    showMenu = false;
                    break;
                case 1:
                    printWeapons();
                    buyWeapon();
                    break;
                case 2:
                    printArmors();
                    buyArmor();
                    break;
            }
        }
        return true;
    }
    public void printWeapons(){
        System.out.println("----Weapons----");
        System.out.println();
        for(Weapons w : Weapons.weapons()){
            System.out.println((w.getId())+". Weapon : " +w.getName()
                    +"\n   Damage : "+w.getDamage()
                    +"\n   Price  : "+w.getPrice());
        }
        System.out.println();
        System.out.println("0 - Exit the store");
    }
    public void buyWeapon(){
        System.out.print("Your Choice : ");
        int selectWeaponID = scan.nextInt();
        System.out.println();
        while (selectWeaponID<0 || selectWeaponID> Weapons.weapons().length){
            System.out.print("Your Choice is invalid. Choose Again : ");
            selectWeaponID = scan.nextInt();
        }
        if (selectWeaponID!=0){
            Weapons selectedWeapon = Weapons.getWeaponsObjByID(selectWeaponID);
            if(selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Ops!! You don't have enough money.");
                    System.out.println();
                } else {
                    System.out.println(selectedWeapon.getName() + " is yours !!");
                    System.out.println("Take care of it, " + this.getPlayer().getCharName() + " " + this.getPlayer().getName() +".");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Available Balance : " + this.getPlayer().getMoney());
                    System.out.println("Your Old Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Your New Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                    System.out.println();
                }
            }
        }
    }
    public void printArmors(){
        System.out.println("----Armors----");
        System.out.println();
        for(Armors a : Armors.armors()){
            System.out.println((a.getId())+". Armor   : "+a.getName()
                    +"\n   Defense : "+a.getDefense()
                    +"\n   Price   : "+a.getPrice());
        }
    }
    public void buyArmor(){
        System.out.print("Your Choice : ");
        int selectArmorID = scan.nextInt();
        System.out.println();
        while (selectArmorID<0 || selectArmorID> Armors.armors().length){
            System.out.print("Your Choice is invalid. Choose Again : ");
            selectArmorID = scan.nextInt();
        }
        if(selectArmorID!=0){
            Armors selectedArmor = Armors.getArmorsObjByID(selectArmorID);
            if(selectedArmor != null){
                if(selectedArmor.getPrice()>this.getPlayer().getMoney()){
                    System.out.println("Ops!! You don't have enough money.");
                    System.out.println();
                }else{
                    System.out.println(selectedArmor.getName()+" is yours !!");
                    System.out.println("Take care of it, "+getPlayer().getCharName()+ " " + this.getPlayer().getName() + ".");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Available Balance : " + this.getPlayer().getMoney());
                    System.out.println("Your Old Armor  : "+ this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Your New Armor  : "+ this.getPlayer().getInventory().getArmor().getName());
                    System.out.println();
                }
            }
        }
    }
}
