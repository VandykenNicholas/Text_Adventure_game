import java.util.Objects;
import java.util.Scanner;

//Scanner scanner = new Scanner(System.in);

public class MainGame {

    /////   Player
    public static String heroName;
    public static int playerHealth = 10;
    public static int playerDefense = 1;
    public static int playerDex = 1;
    public static int playerAttack = 1;

    //// enemy
    public static int enemyHealth = 10;
    public static int enemyDefense = 1;
    public static int enemyDex = 1;
    public static int enemyAttack = 1;
    public static int EnemyPointCount = 10;

    ////// extra
    public static int potions;
    public static int points = 10;

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


    public static void StartGame(){
        wait(.5F);
        System.out.println("Help!");
        wait(.5F);
        System.out.println("Create a hero for us so we can defeat this monster!");
        wait(1F);
        CharacterCreator();
    }



    public static void CharacterCreator(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lets create your Hero!");
        wait(.5f);
        System.out.println("What is your hero's Name?");
        heroName = scanner.nextLine();
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("You have 10 points to distribute to your stats");
        wait(.5f);
        System.out.println("""
                Health affects how much health your character will have. 1 point adds 10 hit points.
                Defense affects the damage reduction you receive from the monster. Each point decreases the attack by 10%
                Attack affects your base damage. Each point increases your base attack by 1 points
                Dex points affects your critical chance. Each points adds a 5% critical chance""");
        wait(1f);
        System.out.println("-------------------------------------------");
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
        CreateEnemy();

    }

    public static void CreateEnemy(){
        enemyHealth += Enemy();
        enemyAttack += Enemy();
        enemyDefense += Enemy();
        enemyDex += EnemyPointCount;
        System.out.println("The monster approached");
        wait(1.0f);
        System.out.println("His state are:\n");
        System.out.println("-------------------------------------------");
        System.out.println(" health points are: " + enemyHealth);
        System.out.println(" Defense points are: " + enemyDefense);
        System.out.println(" Attack points are: " + enemyAttack);
        System.out.println(" Dex points are: " + enemyDex);
        System.out.println("-------------------------------------------");
        wait(1.0f);
        System.out.println("Its time to FIGHT!!!!");
        Battle();
    }

    public static void Battle(){
        wait(1f);
        while(enemyHealth > 0 && playerHealth > 0){
            System.out.println("-------------------------------------------");
            wait(1.0f);
            System.out.println(heroName +" goes in for an attack!");
            enemyHealth = Attack(playerAttack,playerDex,enemyHealth,enemyDefense);
            System.out.println("-------------------------------------------");
            wait(1.0f);
            if (enemyHealth > 0){
                System.out.println("The enemy now has a health of: " + enemyHealth);
                System.out.println("-------------------------------------------");
                wait(1.0f);
                System.out.println("The monster goes in for an attack!");
                playerHealth = Attack(enemyAttack,enemyDex,playerHealth,playerDefense);
                System.out.println("-------------------------------------------");
                wait(1.0f);
                System.out.println(heroName +"'s Health is now at: " + playerHealth);
                System.out.println("-------------------------------------------");
                wait(1.0f);
                if(potions > 0){
                    System.out.println("-------------------------------------------");
                    wait(1.0f);
                    Potion();
                    System.out.println("-------------------------------------------");
                    wait(1.0f);
                }
            }
            wait(3f);

        }
        if (enemyHealth <= 0){
            System.out.println("THE MONSTER WAS SLAIN!!!!! \n You have WON!!!!");
            wait(1F);
            System.out.println("Thank you brave Hero!");
        } else if (playerHealth <= 0) {
            System.out.println("The HERO HAS FALLEN!!!!!");
            wait(2f);
            System.out.println("GAME OVER!!!!!");
        }

    }

    public static void Potion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to use a potion? \n \"Y / N \"?");
        String answer = scanner.nextLine().toLowerCase();
        if (Objects.equals(answer, "y")){
            playerHealth += 5;
            potions --;
            System.out.println("-------------------------------------------");
            wait(1.0f);
            System.out.println(heroName + ", Now has the hit points of: "+ playerHealth);
        }
    }

    public static int Attack(int attackerAtt,int attackDex, int defenderH, int defenderD){
        int localAttack = attackerAtt;
        if(CritChance(attackDex)){
            localAttack = (int) (localAttack + (localAttack * (1.5)));
            wait(1f);
            System.out.println("CRITICAL HIT!!!!");
            System.out.println("-------------------------------------------");
            wait(1.0f);
        }
        System.out.println("The attack has the value of: " + (localAttack - (localAttack * ((.1) * defenderD))));
        System.out.println("-------------------------------------------");
        wait(1.0f);
        return  (int) (defenderH - (localAttack - (localAttack * ((.1) * defenderD))));

    }

    public static boolean CritChance(int dex){
        int chance = (int)Math.floor(Math.random()*(10-1+1)+1);
        return (chance <= dex/2);
    }



    public static int Enemy(){
        if (EnemyPointCount > 0){
            int pointDeduct = (int)Math.floor(Math.random()*(EnemyPointCount-1+1)+1);
            EnemyPointCount -= pointDeduct;
            return pointDeduct;
        }
        return 0;
    }


    public static void PrintStats(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("\nAvailable points:" +points +"\n");
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println(heroName + " health points are: " + playerHealth);
        System.out.println(heroName + " Defense points are: " + playerDefense);
        System.out.println(heroName + " Attack points are: " + playerAttack);
        System.out.println(heroName + " Dex points are: " + playerDex);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("\nAnswering: \n H : increases Health \n D: increases Defense \n A: increases Attack \n X: increases Dex");
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
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


