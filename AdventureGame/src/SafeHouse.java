public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(1,player, "Safe House");
    }
    @Override
    public boolean onLocation(){
        System.out.println();
        System.out.println("You are in the Safe House.");
        System.out.println("Currently Health : " + this.getPlayer().getHealth());
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        System.out.println();
        System.out.println("Your Health is restored.");
        System.out.println("Now Your Health  : "+ this.getPlayer().getHealth());
        System.out.println();
        win();
        return true;
    }
    public void win(){
        if(this.getPlayer().getInventory().isFood() && this.getPlayer().getInventory().isFirewood() && this.getPlayer().getInventory().isWater()) {
            this.getPlayer().setWin(true);
        }
    }
}
