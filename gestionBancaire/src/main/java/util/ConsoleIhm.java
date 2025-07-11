package ihm;

import entity.Customer;
import entity.BankAccount;
import service.CustomerService;
import service.BankAccountService;

import java.util.Scanner;

public class ConsoleIhm {
    private final Scanner scanner = new Scanner(System.in);
    private final CustomerService customerService = new CustomerService();
    private final BankAccountService bankAccountService = new BankAccountService();

    public void start() {
        while (true) {
            System.out.println("""
                    
                    === MENU BANCAIRE ===
                    1. Créer un client
                    2. Dépôt d'argent
                    3. Retrait d'argent
                    4. Afficher un compte
                    5. Quitter
                    """);
            System.out.print("Choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createCustomer();
                case "2" -> deposit();
                case "3" -> withdraw();
                case "4" -> displayAccount();
                case "5" -> {
                    System.out.println("Fermeture de l'application...");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private void createCustomer() {
        System.out.print("Prénom : ");
        String firstname = scanner.nextLine();
        System.out.print("Nom : ");
        String lastname = scanner.nextLine();
        System.out.print("Téléphone : ");
        String phone = scanner.nextLine();

        Customer customer = Customer.builder()
                .firstname(firstname)
                .lastname(lastname)
                .phone(phone)
                .build();

        customerService.save(customer);
    }

    private void deposit() {
        System.out.print("ID du compte : ");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.print("Montant à déposer : ");
        double amount = Double.parseDouble(scanner.nextLine());

        bankAccountService.deposit(accountId, amount);
    }

    private void withdraw() {
        System.out.print("ID du compte : ");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.print("Montant à retirer : ");
        double amount = Double.parseDouble(scanner.nextLine());

        bankAccountService.withdraw(accountId, amount);
    }

    private void displayAccount() {
        System.out.print("ID du compte : ");
        int accountId = Integer.parseInt(scanner.nextLine());

        BankAccount account = bankAccountService.findById(accountId);
        if (account != null) {
            System.out.println("Compte trouvé : " + account);
        } else {
            System.out.println("Aucun compte trouvé avec cet ID.");
        }
    }
}
