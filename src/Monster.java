/*
Ivan Alier-Reyes
October 16th 2022
LSD Pokemon
 */
import utilities.Dice;

import java.util.ArrayList;
import java.util.List;

public abstract class Monster {
    private int defenseMin = 1;
    private int defenseMax = 10;
    private int attackMin = 1;
    private int attackMax = 10;
    private static double MAX_HP = 20.0;
    private String name = "";
    private String phrase = "";
    private Double healthPoints = MAX_HP;
    protected int defensePoints = 10;
    protected int attackPoints = 10;
    private boolean fainted = false;
    private List<ElementalType> elements = new ArrayList<>();

    protected enum ElementalType {
        FIRE, WATER, ELECTRIC, GRASS, NORMAL
    }


    public Monster(String name, ElementalType element) {
        this.name = name;
        elements.add(element);
        Monster.setPhrase(this);
    }

    public double attack(Monster monster) {
        /*
        This method takes a Monster object as a parameter and calculates the attack points the current object will use against it. The Monster object being passed in is the Monster being attacked.

        If the current Monster has fainted, print the following:

        "{name} isn't conscious… it can't attack."
        {name} is whatever value has been assigned as the name to the current object.
        And return 0.0

        Otherwise print:

        "{name} is attacking {monster name}"
        where {name} is the name of the current object and {monster name} is the name from the monster object that was passed in to the method.

        Print the result of the getPhrase() method.

        Calculate the value for the attack by calling calculateAttackPoints() passing in the current object and the elemental types of the monster being attacked.

        Print the following to the screen
        "{name} is attacking with {attack value}"
        where {name} is the name of the current object and {attack value} the result from calling calculateAttackPoints().

        Calculate how much damage was incurred by calling the takeDamage() method of the monster that is being attacked and store the result.

        If the current object is equal to the monster object being attack and the value returned from takeDamage() is greater than 0 print the following

        "{name} hurt itself in the confusion."
        where {name} is the name of the current object

        Return the value that was calculated when takeDamage() was called
         */
        if (this.isFainted()) {
            System.out.println(this.name + " isn't conscious… it can't attack.");
            return 0.0;
        }

            System.out.println(this.name + " is attacking " + monster.name);
            System.out.println(getPhrase());
            double attackPoints = calculateAttackPoints(this, monster.getElements());
            System.out.println(this.name + " is attacking with " + attackPoints + " attack points");
            attackPoints = monster.takeDamage(attackPoints);

            if (this.equals(monster) && attackPoints > 0) {
                System.out.println(this.name + " hurt itself in the confusion.");
            }
            return attackPoints;

    }

    protected double attackModifier(ElementalType defending) {
        double modifier = 1.0;
        /*
        This method takes an ElementalType as a parameter and returns a double value. The ElementalType passed in is the ElementalType of the Monster that is being attacked.

         */
        switch(defending){
            case ELECTRIC:
                if(elements.contains(ElementalType.ELECTRIC)){
                    modifier = 0.5;
                }
                break;
            case FIRE:

                if(elements.contains(ElementalType.FIRE)){
                    modifier = 0.5;
                }
                break;
            case GRASS:
                if(elements.contains(ElementalType.GRASS)){
                    modifier = 0.5;
                }
                break;
            case WATER:
                if(elements.contains(ElementalType.WATER)){
                    modifier = 0.5;
                }
                break;
        }
        return modifier;
    }

    public double calculateAttackPoints(Monster monster, List<ElementalType> enemyTypes) {
        /*
        Use the method roll from the Util Dice class to calculate the attack value for the passed in Monster.

        Call Util.roll() Dice.roll() with the value for attackMin and attackMax of the passed in Monster object. Store the result. This will be our attack value.

        Create a double named modifier and set it to 1.0.

        Print the following
        "{name} rolls a {attack points}"
        where {name} is the name of the passed in Monster object and {attack points} is the value returned from the call to Util.roll() Dice.roll().

        The value for modifier is multiplied by the value returned from calling attackModifier() with each of the enemyTypes that were passed to calculateAttackPoints().

        If the value for modifier is greater than or equal to 2.0 then print
        "It's su-- *cough* very effective!"

        Return the attack value for multiplied by the modifier.

         */
        double modifier = 1.0;
        double roll = Dice.roll(monster.getAttackMin(), monster.getAttackMax());
        System.out.println(monster.name + " rolls a " + roll);
        for (ElementalType type : enemyTypes) {
            modifier *= attackModifier(type);
        }
        if (modifier >= 2.0) {
            System.out.println("It's su-- *cough* very effective!");
        }
        return modifier * roll;

    }

    protected double calculateDefensePoints(Monster monster){
        /*
        Use the method roll from the Util Dice class in the included utilities package to calculate the defense points for the passed in monster.
        Call Util.roll() Dice.roll() with the minimum defense value and the maximum defense value of the Monster object that was passed in as a parameter. This becomes our defenseValue.

        If the defenseValue is even and less than half the maximum defense of the passed in monster,
        add 1 to the defenseValue then multiply the result by 2. Print
        "{name} finds courage in the desperate situation"
        where {name} is the name of the passed in object

        Otherwise if the defenseValue is equal to the minimum defense of the Monster that was passed in print:
        "{name} is clearly not paying attention."
        where {name} is the name of the current object

        Return the defenseValue
         */
        int roll = Dice.roll(monster.defenseMin, monster.defenseMax);

        if(roll < (monster.defenseMax / 2.0) && roll % 2.0 == 0) {
            System.out.println(monster.getName() + " finds courage in the desperate situation");
            roll += 1.0;
            roll *= 2.0;
        } else if (roll == monster.defenseMin) {
            System.out.println(monster.name + " is clearly not paying attention.");
        }

        return roll;

    }

    public boolean isFainted() {
        return false;
    }

    public static int roll(int min) {
        return 0;
    }

    public static int roll(int min, int max) {
        return 0;
    }

    private static Monster setPhrase(Monster m) {
        /*
        setPhrase() will assign a phrase to each individual concrete implementation of Monster.
        Each monster gets the following phrase according to the type of Monster.
        use the setPhraase(string) of the passed in Monster(monster) to set the phrase.
        Electric Rat phrase is "'Lectric!"
        Fire Lizard phrase is "Deal with it"
        Flower Dino phrase is "Flowah!"
        Weird Turtle phrase is "'Urtle"
        unknown or undefined type "No phrase for me!
         */
        String phrase = "No phrase for me!";

        if (m instanceof ElectricRat) {
            phrase = "'Lectric!";
        } else if (m instanceof FireLizard) {
            phrase = "Deal with it";
        } else if (m instanceof FlowerDino) {
            phrase = "Flowah!";
        } else if (m instanceof WeirdTurtle) {
            phrase = "'Urtle";
        }
        m.setPhrase(phrase);
        return m;
    }

    private double takeDamage(Double attackPoints) {
        /*
        takeDamage() uses the passed in 'attackValue' to calculate how much damage, if any, the monster takes.
        First calculate the defense points by calling calculateDefensePoints() and storing the results.
        Them calculate attack points by subtracting the defense points from the passed in attack value.

        If the attack points are greater than 0 print
        "{name} is hit for {attack points} damage!"
        Where {name} is the name of the current object and {attack points} is the value calculated for attack points.

        And reduce the current objects health points by the value calculated for attack points.

        If the value for attack points is equal to 0 print
        "{name} is nearly hit!."
        where {name} is the name of the current object

        If the attack points are less than half the value calculated for defense points, print
        "{name} shrugs off the puny attack."
        where {name} is the name of the current object

        If the value for healthPoints for the current object has fallen to 0 or less print
        "{name} has faint-- passed out. It's passed out."
        where {name} is the name of the current object
        Set fainted to true

        Otherwise print
        "{name} has {healthPoints} / {MAX_HP} HP remaining"
        where {name} is the name of the current object. {healthPoints} are the healthPoints of the current object and {MAX_HP} is the value stored for MAX_HP of the current object.

        Return the value that was calculated for attack points.
         */
        double defensePoints = calculateDefensePoints(this);
        double attackValue = attackPoints - defensePoints;

        if (attackValue > 0) {
            System.out.println(this.name + " is hit for " + attackValue + " damage!");
            this.healthPoints -= attackValue;
        } else if (attackValue == 0) {
            System.out.println(this.name + " is nearly hit!");
        } else if (attackValue < (defensePoints / 2.0)) {
            System.out.println(this.name + " shrugs off the puny attack.");
        }

        if (healthPoints <= 0) {
            System.out.println(this.name + " has faint-- passed out. It's passed out.");
            fainted = true;
        } else {
            System.out.println(this.name + " has " + healthPoints + " / " + MAX_HP + " HP remaining");
        }

        return attackValue;
    }

    public int getDefenseMin() {
        return defenseMin;
    }

    public void setDefenseMin(int defenseMin) {
        this.defenseMin = defenseMin;
    }

    public int getDefenseMax() {
        return defenseMax;
    }

    public void setDefenseMax(int defenseMax) {
        this.defenseMax = defenseMax;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }

    public double getMAX_HP() {
        return MAX_HP;
    }

    public void setMAX_HP(double MAX_HP) {
        this.MAX_HP = MAX_HP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhrase() {
        /*
        Returns  "{phrase} {phrase}" where {phrase} is the value stored in the field phrase.
         */
        return phrase + " " + phrase;
    }

    public void setPhrase(String phrase) {

    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public abstract void setDefensePoints(int defensePoints);

    public int getAttackPoints() {
        return attackPoints;
    }

    public abstract void setAttackPoints();

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    public List<ElementalType> getElements() {
        return elements;
    }

    public int setType(ElementalType type) {
        /*
        Used to set the ElementalType of the current object.

        If the type that is passed in already exists in the current objects list of ElementalTypes (elements) print
        "{type} already set!"
        Where {type} is the value passed in to setType.
        Return 1

        If the value returned from attackModifer() called with the passed in type, is greater than 1.0 print:
        "Can't have conflicting types!"
        Return -1

        Otherwise print
        "{name} now has {type}"
        Where {name} is the name of the current object and {type} is the  value passed in to setType.
        Return 0
         */

        if (elements.contains(type)) {
            System.out.println(type + " already set!");
            return 1;
        } else if (attackModifier(type) > 1.0) {
            System.out.println("Can't have conflicting types!");
            return -1;
        } else {
            System.out.println(name + " now has " + type);
            return 0;
        }
    }
    @Override
    public String toString() {
        /*
        If the Monster object being printed has fainted set to false print the following
        "{name} has {currentHp} / {MAX_HP} hp."
        "Elemental type: [Type1{, Type2 …}]"
        Where {name} is the name of the object, {currentHp} is the currentHP value and {MAX_HP} is the value for MAX_HP.
        [Type1{, Type2 …}] should list the type of the current object. If there is more than one type, it should produce a comma separated list of the values.  See the output below for examples.

        If the monster has fainted the output changes to:
        "{name} has fainted."
        "Elemental type: [Type1{, Type2 …}]"

         */
        if (!fainted) {
            return name + " has " + healthPoints + " / " + MAX_HP + " hp." + "Elemental type: " + elements;
        } else {
            return name + " has fainted." + "Elemental type: " + elements;
        }


    }

}
