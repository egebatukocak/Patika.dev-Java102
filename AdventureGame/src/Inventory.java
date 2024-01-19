public class Inventory {
    private Weapons weapon;
    private Armors armor;
    private boolean water;
    private boolean firewood;
    private boolean food;
    public Inventory() {
        this.weapon = new Weapons(-1,"Bare Hand",0, 0);
        this.armor = new Armors(-1,"Scrap",0,0);
        this.water = false;
        this.firewood = false;
        this.food = false;
    }
    public Weapons getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }
    public Armors getArmor() {
        return armor;
    }
    public void setArmor(Armors armor) {
        this.armor = armor;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }
}
