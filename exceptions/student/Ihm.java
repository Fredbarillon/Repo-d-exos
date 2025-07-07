package exceptions.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ihm {
    Scanner scanner = new Scanner(System.in);
    List<Student> students = new ArrayList<>();

    public void start() {
        while (true) {
            System.out.println(menu());

            String entry = scanner.nextLine();

            switch (entry) {
                case "1" -> createStudent();
                case "2" -> displayStudents();
                default -> {
                    System.out.println("fin du programme");
                    return;
                }
            }
        }
    }

    private String menu() {
        return """
                1: créer un étudiant
                2: afficher les étudiants
                """;
    }

    private void createStudent() {
        System.out.println(" Création d'un étudiant");
        System.out.print("Entrer un nom d'étudiant : ");
        String name = scanner.nextLine();

        System.out.print("Entrer l'age : ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
            if (age < 0) {
                throw new InvalidAgeException("Âge invalide : ne peut être négatif");
            }
        } catch (InvalidAgeException e) {
            System.out.println("Erreur : " + e.getMessage());
            return;
        }
            Student student = new Student(name, age);
            students.add(student);
            System.out.println(" étudiant créé et ajouté à la liste : " + student + "\n");

    }

    private void displayStudents() {
        System.out.println("Afficher les étudiants");
        for (Student s : students) {
            System.out.println(s);
        }

    }
}
