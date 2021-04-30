package hu.nive.ujratervezes.kepesitovizsga.army;

public class HeavyCavalry extends MilitaryUnit {


    private static final int HIT_POINTS= 150;
    private static final int DAMAGE = 20;

    private int attackNumber = 0;

    public HeavyCavalry() {
        super(HIT_POINTS, DAMAGE, true);
    }

    @Override
    public int doDamage() {
        attackNumber++;
        if (attackNumber == 1) {
            return 3 * super.doDamage();
        } else {
            return super.doDamage();
        }
    }

}
