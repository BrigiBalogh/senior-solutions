package hu.nive.ujratervezes.kepesitovizsga.army;

public abstract class  MilitaryUnit {

    private int hitPoints;
    private int  damage;
    private boolean  armor;

    public MilitaryUnit(int hitPoints, int damage, boolean armor) {
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.armor = armor;
    }

    public  int doDamage() {
        return damage;
    }


    public  void sufferDamage(int damage){
           hitPoints -= damage / (armor ? 2 : 1);


    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isArmor() {
        return armor;
    }
}
