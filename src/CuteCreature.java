/**
 * Author: Ian Doarn
 * Professor: Kriangsiri Malasiri
 */

import java.util.Random;
import java.util.Arrays;

public class CuteCreature {

    /* Private variables and stats */

    // Random possible names
    private String[] possibleSpeicesNames = new String[]{
            "Dinotuff", "Goritine", "Hyenithe", "Parraza",
            "Spidepi", "Spinbat", "Electrisel", "Starilla",
            "Bonish", "Propelican", "Beauty-Rex", "Elapip",
            "Pigeoplume", "Cobreon", "Elepheo", "Ashling",
            "Quickmadillo", "Aquateen", "Aquatoth", "Rhinova",
            "Pandarkness", "Flamipod", "Scorpibyss", "Biseaf",
            "Albatrepi", "Starkey", "Chimemite", "Voltorb",
            "Gothoon", "Frogre", "Caesardine", "Batric",
            "Porcupix", "Barracotto", "Dolphoal", "Kineron",
            "Skelequito", "Quickoyote", "Hypeton", "Squidol"
    };

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
    private final boolean isEvolved = false;
    private String elementalAttribution = "";
    private int requiredtotalExp = 250;
    private String[] possibleElementalType = new String[]{"Fire", "Water", "Earth", "Air"};

    /*Private tools*/
    private Random random = new Random();

    /* Getters */
    public String getSpecies() {
        return this.species;
    }
    public int getLevel() {
        return this.level;
    }
    public int getCurrentHitPoints() {
        return this.currentHitPoints;
    }
    public int getMaximumHitPoints() {
        return this.maximumHitPoints;
    }
    public int getAttackDamage() {
        return this.attackDamage;
    }
    public int getExperiencePoints() {
        return this.experiencePoints;
    }
    public int getExperienceValue() {
        return this.experienceValue;
    }
    public boolean getIsSpecial() {
        return this.isSpecial;
    }
    public boolean getIsFainted() {
        return this.isFainted;
    }
    public String getElementalAttribution() {
        return this.elementalAttribution;
    }
    public boolean getIsEvolved() {
        return this.isEvolved;
    }

    /* Setters */
    public void setMaximumHitPoints(int maxHP) {
        this.maximumHitPoints = maxHP;
    }
    public void setSpecies(String s) {
        this.species = s;
    }
    public void setCurrentHitPoints(int currentHP) {
        this.currentHitPoints = currentHP;
    }
    public void setAttackDamage(int attackDmg) {
        this.attackDamage = attackDmg;
    }
    public void setExperiencePoints(int xp) {
        gainExp(xp);
    }
    public void setExperienceValue(int xpValue) {
        this.experienceValue = xpValue;
    }
    public void setSpecial(boolean value) {
        this.isSpecial = value;
    }
    public void setFainted(boolean value) {
        this.isFainted = value;
    }
    public void setElementalAttribution(String attribute) {
        if (Arrays.asList(possibleElementalType).contains(attribute))
            this.elementalAttribution = attribute;
        else
            System.out.println("attribute = [" + attribute + "] is not a valid ElementType");
    }
    public void setRequiredtotalExp(int totalXP) {
        this.requiredtotalExp = totalXP;
    }

    public CuteCreature() {
        // Used to create random Creatures!
        species = setRandomSpeciesName();
        maximumHitPoints = setRandomHitPoints();
        currentHitPoints = maximumHitPoints;
        experienceValue = setRandomXPValue();
        isSpecial = setRandomSpecialChance();
    }

    public CuteCreature(String nSpecies, int nHP, int nAttackDmg, int nXP, boolean IsSpecial) {
        // Used if specifying specific creature
        species = nSpecies;
        maximumHitPoints = nHP;
        currentHitPoints = maximumHitPoints;
        experienceValue = nXP;
        attackDamage = nAttackDmg;
        isSpecial = IsSpecial;
    }

    protected String setRandomSpeciesName() {
        // Generate random species name.
        return possibleSpeicesNames[(int) (Math.random() * possibleSpeicesNames.length)];
    }

    protected int setRandomHitPoints() {
        // Sets random amount of HP to the creature.
        return level * (20 / 4) + ((int) (Math.random() * 10));
    }

    protected int setRandomXPValue() {
        // Sets random amount of XP creature is worth.
        return level * (20 / 4) + ((int) (Math.random() * 10));
    }

    protected boolean setRandomSpecialChance() {
        // Determines if the creature will be special
        return ((Math.random() * 10) == (Math.random() * 10));
    }

    public void takeDamage(int dmg) {
        // Damage creature, cause creature to faint if dmg greater than current HP

        // Wether his it critical or not
        String dmgLevel = "";

        if (dmg % 2 == 0)
            dmgLevel = "Critical Hit!";
        else
            dmgLevel = "Hit!";

        if (currentHitPoints - dmg > 0) {
            currentHitPoints -= dmg;
            System.out.println(String.format("%s %s took %s damage!", dmgLevel, species, dmg));
            return;
        }
        if (currentHitPoints - dmg <= 0) {
            currentHitPoints -= dmg;
            isFainted = true;
            System.out.println(String.format("%s %s took %s damage!", dmgLevel, species, dmg));
            System.out.println(species + " fainted!");
        }
    }

    public void takeDamage(int dmg, String element) {
        // Damage creature, cause creature to faint if dmg greater than current HP
        // Displays elemental damage
        // Wether his it critical or not
        String dmgLevel = "";

        if (dmg % 2 == 0)
            dmgLevel = "Critical Hit!";
        else
            dmgLevel = "Hit!";

        if (currentHitPoints - dmg > 0) {
            currentHitPoints -= dmg;
            System.out.println(String.format("%s %s took %s %s damage!", dmgLevel, species, dmg, element));
            return;
        }
        if (currentHitPoints - dmg <= 0) {
            currentHitPoints -= dmg;
            isFainted = true;
            System.out.println(String.format("%s %s took %s %s damage!", dmgLevel, species, dmg, element));
            System.out.println(species + " fainted!");
        }
    }

    private void levelUp() {
        // Level up creature and increase stats

        // Increment level
        level++;

        // Upgrade stats
        if (level <= 10 || level >= 2) {
            currentHitPoints += 4;
            maximumHitPoints += 4;
            attackDamage += 3;
        } else if (level > 11) {
            currentHitPoints += 1;
            maximumHitPoints += 1;
            attackDamage += 1;
        }

        experienceValue += 10;

        System.out.println(String.format("%s has leveled up! %s is now %s", species, species, level));
    }

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

    public void attack(CuteCreature c) {
        /*
            Attacks another cute creature!

            Attacks have:
                - 80% chance to hit
                - 5% chance to critical hit
                - 15% chance to miss

            if the attack hits, the damage done will
            be within +/- 20% of the creatures attackDamage.

            A critical hit doubles the damage done.

            If the target CuteCreature is defeated,
            then the attacking creature will gain the
            defeated creatures experience.
         */

        // Generate random values for chance variables
        double criticalChance = Math.random();
        double hitChance = Math.random();
        double missChance = Math.random();
        double damageChance = Math.random(); // Round to first decimal place
        boolean isCritical = false;

        System.out.println(String.format("%s attacked %s!", species, c.getSpecies()));

        // 5% critical chance
        if (criticalChance < 0.05)
            isCritical = true;

        // 80% chance
        if (hitChance < 0.8) {
            int damage = attackDamage;

            // %20 percent chance to do +/- damage
            if (damageChance < 0.5) {
                // Add 20% more
                damage += (int) (attackDamage * (20.0f / 100.0f));
            } else if (damageChance > 0.5) {
                // Remove 20%
                damage = attackDamage;
                damage -= (int) (attackDamage * (20.0f / 100.0f));
            } else if (damageChance == 0.5) {
                // Do exact attackDamage
                damage = attackDamage;
            }

            // Check if we can critical hit
            if (isCritical)
                damage *= 2; // Double our damage

            c.takeDamage(damage);

            // check to see if attacking creature fainted
            // if they did, get their XP
            if (c.getIsFainted()) {
                gainExp(c.getExperienceValue());
            }

        } else if (missChance < 0.15) {
            System.out.println("Miss!");
        }

    }

    public String toString() {
        /*
            Overrides the toString method
            to print stats and information about
            the current Cute Creature object
         */
        StringBuilder sb = new StringBuilder();

        // Title
        // Example: Level 1 Bowlbasore
        String title = String.format("Level %s %s", level, species);

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
        if (isSpecial) {
            sb.append("*** Special! ***");
            sb.append("\n");
        }

        // Add stats: HP, Attack Dmg, XP, XP Value
        sb.append(String.format("HP: %s/%s", currentHitPoints, maximumHitPoints));
        sb.append("\n");
        sb.append(String.format("Attack Dmg: %s", attackDamage));
        sb.append("\n");
        sb.append(String.format("XP: %s/%s", experiencePoints, requiredtotalExp));
        sb.append("\n");
        sb.append(String.format("XP Value: %s", experienceValue));

        // Return final string
        return sb.toString();
    }
}
