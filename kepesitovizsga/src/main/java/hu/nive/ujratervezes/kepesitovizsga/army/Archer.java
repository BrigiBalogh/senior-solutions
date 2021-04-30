package hu.nive.ujratervezes.kepesitovizsga.army;

public class Archer extends MilitaryUnit {

   private static final int HIT_POINTS= 50;
    private static final int DAMAGE = 20;

    public Archer() {
        super(HIT_POINTS, DAMAGE, false);
    }

}
