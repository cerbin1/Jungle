package game;

import java.io.IOException;
import java.lang.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game(4, 8);
        game.displayBoard();
        while (true) {
            System.out.println("wasd to move, q to exit");
            Scanner scanner = new Scanner(System.in);
            try {
                char userInput = scanner.next().charAt(0);
                if (userInput == 'q') {
                    System.out.println("Quiting game...");
                    break;
                }
                if (isProperlyMove(userInput)) {
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

    private static boolean isProperlyMove(java.lang.Character input) {
        return input == 'w' || input == 'a' || input == 's' || input == 'd';
    }
}
