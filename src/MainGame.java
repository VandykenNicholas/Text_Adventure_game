import java.util.Objects;
import java.util.Scanner;

//Scanner scanner = new Scanner(System.in);

public class MainGame {

    /////   Player
    public static String heroName;
    public static int playerHealth;
    public static int playerDefense;
    public static int playerDex;
    public static int playerAttack;

    //// enemy
    public static int EnemyHealth;
    public static int enemyDefense;
    public static int enemyDex;
    public static int enemyAttack;

    ////// extra
    public static int potions;
    public static int points = 10;


    public static void StartGame(){
        wait(.5F);
        System.out.println("Help!");
        wait(.5F);
        System.out.println("Create a hero for us so we can defeat this monster!");
        wait(1F);
        CharacterCreator();
    }

    public static void Welcome(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the hero game!");
        wait(.5F);
        System.out.println("are you ready to play?");
        wait(.5F);
        System.out.println("Answer \"Y/ or N\"");
        String letsPlay = scanner.nextLine().toLowerCase();
        if (Objects.equals(letsPlay, "y")){
            StartGame();
        }
    }



    public static void CharacterCreator(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lets create your Hero!");
        wait(.5f);
        System.out.println("What is your hero's Name?");
        heroName = scanner.nextLine();
        System.out.println("You have 10 points to distribute to your stats");
        wait(.5f);
        System.out.println("""
                Health affects how much health your character will have. 1 point adds 10 hit points.
                Defense affects the damage reduction you receive from the monster. Each point decreases the attack by 10%
                Attack affects your base damage. Each point increases your base attack by 3 points
                Dex points affects your critical chance. Each points adds a 5% critical chance""");
        wait(1f);
        System.out.println("-------------------------------------------\n");
        while(points > 0){
            PrintStats();
        }
        System.out.println("Now we will roll a dice to see how many potions you will get");
        wait(1f);
        System.out.println("Rolling.....");
        wait(1f);
        potions = (int)Math.floor(Math.random()*(6-1+1)+1);
        System.out.println("You have : " +potions+" potions");
        wait(1f);
        Battle();
    }

    public static void Battle(){

    }

    public static void PrintStats(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAvailable points:" +points +"\n");
        System.out.println("-------------------------------------------\n");
        System.out.println(heroName + " health points are: " + playerHealth);
        System.out.println(heroName + " Defense points are: " + playerDefense);
        System.out.println(heroName + " Attack points are: " + playerAttack);
        System.out.println(heroName + " Dex points are: " + playerDex);
        System.out.println("-------------------------------------------\n");
        System.out.println("\nAnswering: \n H : increases Health \n D: increases Defense \n A: increases Attack \n X: increases Dex");
        System.out.println("-------------------------------------------\n");
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "h" -> playerHealth++;
            case "d" -> playerDefense++;
            case "a" -> playerAttack++;
            case "x" -> playerDex++;
        }
        points --;
    }

    public static void wait(float secondsToSleep){
        try {
            Thread.sleep((long) (secondsToSleep * 1000L));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }


    public static void main(String[] args) {
            Welcome();
    }


}


