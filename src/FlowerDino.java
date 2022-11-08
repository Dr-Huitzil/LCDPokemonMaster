/*
Ivan Alier-Reyes
October 16th 2022
LSD Pokemon
 */
import utilities.Dice;

public class FlowerDino extends Monster {
    private final int DEFENSE_MIN = 4;
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 3;
    private final int ATTACK_MAX = 6;

    public FlowerDino(String name) {
        super(name, ElementalType.GRASS);
        setAttackPoints(Dice.roll(ATTACK_MIN, ATTACK_MAX));
        setDefensePoints(Dice.roll(DEFENSE_MIN, DEFENSE_MAX));
    }

    public void setAttackPoints() {
        //uses Dice.roll with the calues for ATTACK_MIN and ATTACK_MAX to set the attack points
        int roll = Dice.roll(ATTACK_MIN, ATTACK_MAX);
        setAttackPoints(roll);
    }

    public void setDefensePoints() {
        //uses Dice.roll with the calues for DEFENSE_MIN and DEFENSE_MAX to set the defense points
        super.setDefensePoints(Dice.roll(DEFENSE_MIN, DEFENSE_MAX));
    }
}
