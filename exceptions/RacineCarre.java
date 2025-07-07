package exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RacineCarre {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int input;
        while (!valid) {
            System.out.print("Entrer un chiffre : ");
            try {
                input = scanner.nextInt();
                if (input < 0 ) {
                    throw new IllegalArgumentException("Le nombre doit être positif");
                }
                System.out.println("La racine carrée de " + input + " est : " + Math.sqrt(input));
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }
}
