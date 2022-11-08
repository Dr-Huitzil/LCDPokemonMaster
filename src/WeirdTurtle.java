/*
Ivan Alier-Reyes
October 16th 2022
LSD Pokemon
 */
import utilities.Dice;

public class WeirdTurtle extends Monster{
    private final int DEFENSE_MIN = 3;
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 3;
    private final int ATTACK_MAX = 8;

    public WeirdTurtle(String name){
  /*
  The constructor takes a String value.
  Call the constructor from the parent object with the String value and the ElementalType specified in the types shown below.
  Then use the inherited setters and getters to set the attack_Min/attack_max and defense_min/defense_max values.
  The constructor calls the constructor from the parent with the String value that was passed in and the ElementalType "WATER.
   */
        super(name, ElementalType.WATER);
        setAttackPoints(Dice.roll(ATTACK_MIN, ATTACK_MAX));
        setDefensePoints(Dice.roll(DEFENSE_MIN, DEFENSE_MAX));
    }

    public void setAttackPoints(){
        //uses Dice.roll with the calues for ATTACK_MIN and ATTACK_MAX to set the attack points
        int roll = Dice.roll(ATTACK_MIN, ATTACK_MAX);
        setAttackPoints(roll);
    }


    public void setDefensePoints(){
        //uses Dice.roll with the calues for DEFENSE_MIN and DEFENSE_MAX to set the defense points
    }
}
