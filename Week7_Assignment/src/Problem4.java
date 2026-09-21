import java.util.Scanner;

interface Attackable4 {
    void attack();
    void attack(String weaponName);
}

interface Defendable4 {
    void defend();
}

abstract class GameCharacter4 {
    private static int counter = 0;
    private final int characterId;

    GameCharacter4() {
        counter++;
        characterId = counter;
    }

    public int getCharacterId() {
        return characterId;
    }

    abstract String getSpecialMove();
}

class Warrior4 extends GameCharacter4
        implements Attackable4, Defendable4 {

    private String name;

    Warrior4(String name) {
        this.name = name;
    }

    @Override
    public void attack() {
        System.out.println(name + " attacks with a sword.");
    }

    @Override
    public void attack(String weaponName) {
        System.out.println(name + " attacks with " + weaponName + ".");
    }

    @Override
    public void defend() {
        System.out.println(name + " blocks the attack.");
    }

    @Override
    String getSpecialMove() {
        return name + "'s special move is Power Strike.";
    }
}

class Trap4 implements Defendable4 {
    private String trapName;

    Trap4(String trapName) {
        this.trapName = trapName;
    }

    @Override
    public void defend() {
        System.out.println("Trap " + trapName + " activates its defense.");
    }
}

public class Problem4 {

    static void resolveDefense(Defendable4[] combatants) {

        for (Defendable4 c : combatants) {
            c.defend();
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter warrior name: ");
        String name = in.nextLine();

        Warrior4 warrior = new Warrior4(name);

        System.out.println("\n--- Warrior Details ---");

        warrior.attack();
        warrior.attack("Axe");
        warrior.defend();

        System.out.println(warrior.getSpecialMove());
        System.out.println("Character ID: "
                + warrior.getCharacterId());

        System.out.print("\nEnter trap name: ");
        String trapName = in.nextLine();

        Trap4 trap = new Trap4(trapName);

        System.out.println("\n--- Trap Details ---");
        trap.defend();

        System.out.println("\n--- Mixed Defense ---");

        Defendable4[] combatants = {warrior, trap};

        resolveDefense(combatants);

        in.close();
    }
}
