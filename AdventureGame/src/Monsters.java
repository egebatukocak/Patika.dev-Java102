public class Monsters {
    private int id;
    private int damage;
    private int health;
    private int defHealth;
    private String name;
    private int award;
    public Monsters(int id,String name, int health, int damage, int award) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.defHealth = health;
        this.damage = damage;
        this.award = award;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if(health<0){
            health = 0;
        }
        this.health = health;
    }
    public int getAward() {
        return award;
    }
    public void setAward(int award) {
        this.award = award;
    }
    public int getDefHealth() {
        return defHealth;
    }
    public void setDefHealth(int defHealth) {
        this.defHealth = defHealth;
    }
}
