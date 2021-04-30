package hu.nive.ujratervezes.kepesitovizsga.army;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Army {

    private List<MilitaryUnit> militaryUnits = new ArrayList<>();

    public void addUnit(MilitaryUnit militaryUnit) {
        militaryUnits.add(militaryUnit);

    }

    public int getArmySize() {
        return militaryUnits.size();
    }

    public int getArmyDamage() {
        int sum = 0;
        for (MilitaryUnit militaryUnit : militaryUnits) {
           sum += militaryUnit.doDamage();
        }
        return sum;
    }

    public void damageAll(int damage) {
        Iterator<MilitaryUnit> it = militaryUnits.iterator();
        while (it.hasNext()) {
            MilitaryUnit unit = it.next();
            unit.sufferDamage(damage);
            int hitPoints = unit.getHitPoints();

            if (hitPoints < 25) {
                it.remove();
            }
        }
    }
}
