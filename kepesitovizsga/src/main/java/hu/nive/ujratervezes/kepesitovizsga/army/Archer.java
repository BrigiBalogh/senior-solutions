package hu.nive.ujratervezes.kepesitovizsga.army;

public class Archer extends MilitaryUnit {

   private static final int HIT_POINTS= 50;
    private static final int DAMAGE = 20;

    public Archer(int hitPoints, int damage) {
        super(hitPoints, damage);
    }


    @Override
    public int doDamage() {
        return 20;



}

    @Override
    public boolean isArmor() {
        return false;
    }

    }
