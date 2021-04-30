package hu.nive.ujratervezes.kepesitovizsga.army;

public class Swordsman extends MilitaryUnit {

    private boolean shield = true;
    //private int attackNumber = 0;

    public Swordsman(boolean armor) {
        super(100, 10, armor);
    }

   /* @Override
    public int doDamage() {
        attackNumber++;
        if (attackNumber % 2 == 0) {

        }
        return super.doDamage();
    }*/

    @Override
    public void sufferDamage(int damage) {
        if (shield) {
            shield = false;
        } else {
            super.sufferDamage(damage);
        }
    }
}
