/*
Ivan Alier-Reyes
October 16th 2022
LSD Pokemon
 */

import utilities.Dice;
public class ElectricRat extends Monster{
    private final int DEFENSE_MIN = 5;
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 5;
    private final int ATTACK_MAX = 8;

    public ElectricRat(String name){
        super(name, ElementalType.ELECTRIC);
        setAttackPoints();
    }

    public void setAttackPoints(){
        //uses Dice.roll with the calues for ATTACK_MIN and ATTACK_MAX to set the attack points
        int roll = Dice.roll(ATTACK_MIN, ATTACK_MAX);
        setAttackPoints(roll);
    }

    public void setDefensePoints(){
        //uses Dice.roll with the calues for DEFENSE_MIN and DEFENSE_MAX to set the defense points
        super.setDefensePoints(Dice.roll(DEFENSE_MIN, DEFENSE_MAX));
    }
}
