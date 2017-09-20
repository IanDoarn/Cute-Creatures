import Creatures.CuteCreature;
import Creatures.EvolvableCuteCreature;
public class TestEvolvableCuteCreature {

    public static void main(String[] args) {
        testCreateEvovlableCreature();
    }

    static void testCreateEvovlableCreature(){

        EvolvableCuteCreature eCreature1 = new EvolvableCuteCreature();
        EvolvableCuteCreature eCreature2 = new EvolvableCuteCreature();

        // Set both creatures to level 19
        eCreature1.setLevel(19);
        eCreature2.setLevel(19);

        // Force both creatures to level up
        eCreature1.gainExp(eCreature1.getTotalRequiredXP());
        eCreature2.gainExp(eCreature2.getTotalRequiredXP());
    }
}
