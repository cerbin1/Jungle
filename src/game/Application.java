package game;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game(4, 8);
        game.displayBoard();
        System.out.println("wasd + enter to move, q to exit");
        while (true) {
            try {
                char userInput = scanner.next().charAt(0);
                if (userInput == 'q') {
                    System.out.println("Quitting game...");
                    break;
                }
                if (isCorrectMove(userInput)) {
                    game.makeMove(userInput);
                    game.displayBoard();
                } else {
                    System.out.println("Wrong move!");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean isCorrectMove(java.lang.Character input) {
        return input == 'w' || input == 'a' || input == 's' || input == 'd';
    }
}
