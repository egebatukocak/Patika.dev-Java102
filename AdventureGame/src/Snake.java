public class Snake extends Monsters{
    private int randomDamage;
    private int randomAwardChance;
    public Snake() {
        super(4, "Snake", 12,0,0);
        setRandomDamage();
        setRandomAwardChance();
    }
    public int random(){
        return (int) (Math.random() * 100);
    }
    public int getRandomDamage() {
        return randomDamage;
    }
    public void setRandomDamage() {
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
        this.randomDamage = randomDamage;
    }
    public int getRandomAwardChance() {
        return randomAwardChance;
    }
    public void setRandomAwardChance() {
        this.randomAwardChance = random();
    }
}
