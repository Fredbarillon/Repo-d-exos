package exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            System.out.print("Entrer un chiffre : ");
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input);
                if (! input.equals(String.valueOf(number))) {
                    throw new StringIsNotAnIntException(
                            "Le format entré n'est pas un entier: " + input);
                }
                System.out.println("Le nombre est : " + number);
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }


    }
}
