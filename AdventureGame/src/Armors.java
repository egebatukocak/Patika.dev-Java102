public class Armors {
    private int id;
    private String name;
    private int defense;
    private int price;

    public Armors(int id, String name, int defense, int price) {
        this.id = id;
        this.name = name;
        this.defense = defense;
        this.price = price;
    }
    public static Armors[] armors(){
        Armors[] armorList = new Armors[3];
        armorList[0] = new Armors(1,"Light Armor",1,15);
        armorList[1] = new Armors(2,"Medium Armor",3,25);
        armorList[2] = new Armors(3,"Heavy Armor",5,40);
        return armorList;
    }
    public static Armors getArmorsObjByID(int id){
        for(Armors a : Armors.armors()){
            if(a.getId()==id){
                return a;
            }
        }
        return null;
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
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
