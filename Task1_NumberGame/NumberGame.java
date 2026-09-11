
import java.util.*;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int score =0;
        char choice;
        do{
        int number = random.nextInt(100)+1;
        int attempts = 0;
        int maxAttempts = 5;
        boolean correct = true;
        System.out.println("\nGuess a number between 1 and 100");
        System.out.println("You have 5 attempts.");
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;
                if (guess==number) {
                    System.out.println("Correct");
                    score++;
                    correct = true;
                    break;

                }
                else if (guess > number) {
                    System.out.println("Too high!");
                }
                else {
                    System.out.println("Too low!");
                }
        }
        if (!correct) {
            System.out.println("You lost this round.");
                System.out.println("The number was: " + number);
        }
        System.out.println("Your score: " + score);

            System.out.print("Do you want to play again? (y/n): ");
            choice = sc.next().charAt(0);
        }
        while( choice=='y' || choice =='Y');
            System.out.println("\nFinal Score: " + score);
        System.out.println("Game Over!");

        }
    }

