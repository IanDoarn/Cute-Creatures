import Creatures.CuteCreature;
public class TestCuteCreature {

    public static void main(String[] args) {
        testCreateCreature();
    }

    static void testCreateCreature(){
        CuteCreature creature = new CuteCreature();
        CuteCreature uniqueCreature = new CuteCreature("Dinosaur", 150, 50, 250, true);

        System.out.println(creature);

        System.out.println(uniqueCreature);

        uniqueCreature.attack(creature);

        uniqueCreature.gainExp(2000);

        // Kill the creature
        uniqueCreature.takeDamage(uniqueCreature.getMaximumHitPoints());
    }
}
