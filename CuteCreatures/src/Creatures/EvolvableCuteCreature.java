/**
 * Author: Ian Doarn
 * Professor: Kriangsiri Malasiri
 * <p>
 * Elemental Creatures!
 */
package Creatures;
import java.util.Arrays;
public class EvolvableCuteCreature extends CuteCreature {

    private String species = "";
    private int level = 1;
    private int currentHitPoints = 0;
    private int maximumHitPoints = 0;
    private int attackDamage = 6;
    private int experiencePoints = 0; // Will always be 0 on level up and capture
    private int experienceValue = 0;
    private boolean isSpecial = false;

    /* Other Stuff */
    private boolean isFainted = false;
    private boolean isEvolved = false;
    private String elementalAttribution = "Normal";
    private int requiredtotalExp = 250;

    /* Getters */
    public boolean getIsEvolved() {
        return isEvolved;
    }

    /* Setters */
    public void setEvolved(boolean value) {
        isEvolved = value;
    }


    public EvolvableCuteCreature() {
        // Used to create random Creatures!
        species = super.setRandomSpeciesName();
        maximumHitPoints = super.setRandomHitPoints();
        currentHitPoints = maximumHitPoints;
        experienceValue = super.setRandomXPValue();
        isSpecial = super.setRandomSpecialChance();
    }

    public EvolvableCuteCreature(String nSpecies, int nHP, int nAttackDmg, int nXP, boolean IsSpecial) {
        // Used if specifying specific creature
        super(nSpecies, nHP, nAttackDmg, nXP, IsSpecial);
    }

    public void elementalAttack(CuteCreature c) {
        /*
        Elemental Attack

        Attacks affect creatures differently
        depending on the attack type

        */

        if (!c.getIsEvolved()) {
            c.takeDamage(attackDamage, elementalAttribution);
        } else {
            if (c.getElementalAttribution().equals(elementalAttribution)) {
                c.takeDamage(attackDamage, elementalAttribution);
            } else {
                c.takeDamage(calculateElementalDmg(c.getElementalAttribution()), elementalAttribution);
            }
        }
    }

    private int calculateElementalDmg(String element) {
        // Logic for determining how much damage to deal
        int damage = attackDamage;

        switch (elementalAttribution) {
            case "Fire":
                switch (element) {
                    case "Water":
                        return (damage + (int) (attackDamage * (0.25f / 100.0f)));
                    case "Air":
                        return damage * 4;
                    case "Earth":
                        return damage;
                    default:
                        return damage;
                }
            case "Water":
                switch (element) {
                    case "Earth":
                        return (damage + (int) (attackDamage * (0.25f / 100.0f)));
                    case "Fire":
                        return damage * 4;
                    case "Air":
                        return damage;
                    default:
                        return damage;
                }
            case "Air":
                switch (element) {
                    case "Fire":
                        return (damage + (int) (attackDamage * (0.25f / 100.0f)));
                    case "Earth":
                        return damage * 4;
                    case "Water":
                        return damage;
                    default:
                        return damage;
                }
            case "Earth":
                switch (element) {
                    case "Air":
                        return (damage + (int) (attackDamage * (0.25f / 100.0f)));
                    case "Water":
                        return damage * 4;
                    case "Fire":
                        return damage;
                    default:
                        return damage;
                }
            default:
                return damage;
        }
    }

    private void evolveCreature() {
        // Evolve Creature
        isEvolved = true;
        String newSpecies = super.setRandomSpeciesName();

        // Attune creature with element based
        // on first letter of their name
        char id = newSpecies.toUpperCase().charAt(0);

        if (id >= 'A' && id <= 'G')
            elementalAttribution += "Fire";
        else if (id >= 'H' && id <= 'M')
            elementalAttribution += "Water";
        else if (id >= 'N' && id <= 'S')
            elementalAttribution += "Air";
        else if (id >= 'T' && id <= 'Z')
            elementalAttribution += "Earth";

        maximumHitPoints += 15;
        currentHitPoints = maximumHitPoints;
        attackDamage += 5;

        System.out.println(String.format("%s has evolved into a %s!", species, newSpecies));

        species = newSpecies;
    }

    protected void levelUp() {
        // Level up creature and increase stats
        // Evolve creature if they reach level 20 and
        // have not already evolved

        // Increment level
        level++;

        // Upgrade stats
        if (level <= 10 && level >= 2) {
            maximumHitPoints += 4;
            currentHitPoints = maximumHitPoints;
            attackDamage += 3;
        } else if (level >= 11 && level <= 19) {
            maximumHitPoints += 1;
            currentHitPoints = maximumHitPoints;
            attackDamage += 1;
        } else if (level >= 20) {
            if (!isEvolved) {
                evolveCreature();
            } else {
                maximumHitPoints += 1;
                currentHitPoints = maximumHitPoints;
                attackDamage += 1;
            }
        }

        experienceValue += 10;

        System.out.println(String.format("%s has leveled up! %s is now %s", species, species, level));
    }

    @Override
    public void gainExp(int exp) {
        // Gain xp and level up if necessary

        // Add given exp to creature
        experiencePoints += exp;
        System.out.println(String.format("%s has gained %s experience!", species, exp));

        // Ensure we level up the required amount of times
        // based on the XP we just got
        while (true) {
            // Check to see if we can level up
            if (experiencePoints >= requiredtotalExp) {
                // Level up and increment total required XP
                // By 50
                levelUp();
                experiencePoints -= requiredtotalExp;
                requiredtotalExp += 50;
            } else {
                break;
            }
        }
    }

    @Override
    public String toString() {
        /*
            Overrides the toString method
            to print stats and information about
            the current Cute Creature object
         */
        StringBuilder sb = new StringBuilder();

        // Title
        // Example: Level 1 Bowlbasore
        String title = String.format("Level %s %s", super.getLevel(), super.getSpecies());

        // Little trick to ensure underline for title is
        // the same length as the title!
        // Reference: https://stackoverflow.com/a/2804866
        char[] underline = new char[title.length()];
        Arrays.fill(underline, '-');

        // Add title and underline
        sb.append(title);
        sb.append("\n");
        sb.append(new String(underline));
        sb.append("\n");

        // Added only if creature is special
        if (super.getIsSpecial()) {
            sb.append("*** Special! ***");
            sb.append("\n");
        }

        // Add stats: Element Type, HP, Attack Dmg, XP, XP Value
        sb.append(String.format("Elemental Type: %s", this.elementalAttribution));
        sb.append("\n");
        sb.append(String.format("HP: %s/%s", super.getCurrentHitPoints(), super.getMaximumHitPoints()));
        sb.append("\n");
        sb.append(String.format("Attack Dmg: %s", super.getAttackDamage()));
        sb.append("\n");
        sb.append(String.format("XP: %s/%s", super.getExperiencePoints(), super.getTotalRequiredXP()));
        sb.append("\n");
        sb.append(String.format("XP Value: %s", super.getExperienceValue()));

        // Return final string
        return sb.toString();
    }
}