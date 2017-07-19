package game;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game(40, 20);
        game.displayBoard();
        displayHint();
        while (true) {
            char userInput = scanner.next().charAt(0);
            if (userInput == 'q') {
                System.out.println("Quitting game...");
                System.exit(0);
                break;
            }
            if (isCorrectMove(userInput)) {
                game.makeMove(userInput);
                game.displayBoard();
            } else {
                System.out.println("Wrong move!");
                displayHint();
            }
        }
    }

    private static void displayHint() {
        System.out.println("wasd + enter to move, q to exit");
    }

    static boolean isCorrectMove(java.lang.Character input) {
        return input == 'w' || input == 'a' || input == 's' || input == 'd';
    }
}
