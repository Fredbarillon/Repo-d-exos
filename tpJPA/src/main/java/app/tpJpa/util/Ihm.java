package app.tpJpa.util;

import app.tpJpa.entity.Product;
import app.tpJpa.entity.Product_Electronic;
import app.tpJpa.entity.Product_Food;
import app.tpJpa.entity.Product_Housing;
import app.tpJpa.service.ProductService;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Scanner;

public class Ihm {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductService service = new ProductService();

    public void start() {
        while (true) {
            System.out.println("""
                --- MENU ---
                1. Ajouter un produit food
                2. Ajouter un produit électronique
                3. Ajouter un produit housing
                4. Voir tous les produits
                5. Supprimer un produit
                6. Trouver un produit par ID
                7. Mettre à jour un produit (id + infos)
                0. Quitter
                """);
            System.out.print("Choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addFood();
                case "2" -> addElectronic();
                case "3" -> addHousing();
                case "4" -> service.getAll().forEach(System.out::println);
                case "5" -> {
                    System.out.print("ID à supprimer : ");
                    long id = Long.parseLong(scanner.nextLine());
                    System.out.println(service.delete(id) ? "Supprimé." : "Non trouvé.");
                }
                case "6" -> {
                    System.out.print("ID à chercher : ");
                    long id = Long.parseLong(scanner.nextLine());
                    Product p = service.get(id);
                    System.out.println(p != null ? p : "Non trouvé.");
                }
                case "7" -> updateProduct();
                case "0" -> {
                    System.out.println("À bientôt !");
                    return;
                }
                default -> System.out.println("Option invalide.");
            }
        }
    }

    private void addFood() {
        System.out.print("Nom : ");
        String name = scanner.nextLine();
        System.out.print("Prix : ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Date d'expiration (AAAA-MM-JJ) : ");
        LocalDate expiry = LocalDate.parse(scanner.nextLine());

        Product_Food p = new Product_Food();
        p.setName(name);
        p.setPrice(price);
        p.setExpiryDate(expiry);

        service.save(p);
    }

    private void addElectronic() {
        System.out.print("Nom : ");
        String name = scanner.nextLine();
        System.out.print("Prix : ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Durée de batterie (heures) : ");
        long hours = Long.parseLong(scanner.nextLine());
        Duration batteryCapacity = Duration.ofHours(hours);

        Product_Electronic p = new Product_Electronic();
        p.setName(name);
        p.setPrice(price);
        p.setBatteryCapacity(batteryCapacity);

        service.save(p);
    }

    private void addHousing() {
        System.out.print("Nom : ");
        String name = scanner.nextLine();
        System.out.print("Prix : ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Hauteur : ");
        double height = Double.parseDouble(scanner.nextLine());
        System.out.print("Largeur : ");
        double weight= Double.parseDouble(scanner.nextLine());

        Product_Housing p = new Product_Housing();
        p.setName(name);
        p.setPrice(price);
        p.setHeight(height);
        p.setWeight(weight);

        service.save(p);
    }

    private void updateProduct() {
        System.out.print("ID à mettre à jour : ");
        long id = Long.parseLong(scanner.nextLine());
        Product existing = service.get(id);

        if (existing == null) {
            System.out.println("Produit non trouvé.");
            return;
        }

        System.out.print("Nouveau nom : ");
        String name = scanner.nextLine();
        System.out.print("Nouveau prix : ");
        double price = Double.parseDouble(scanner.nextLine());

        existing.setName(name);
        existing.setPrice(price);

        if (existing instanceof Product_Food food) {
            System.out.print("Nouvelle date d'expiration (AAAA-MM-JJ) : ");
            food.setExpiryDate(LocalDate.parse(scanner.nextLine()));
        } else if (existing instanceof Product_Electronic electronic) {
            System.out.print("Nouvelle capacité de batterie : ");
            long hours = Long.parseLong(scanner.nextLine());
            Duration batteryCapacity = Duration.ofHours(hours);
            electronic.setBatteryCapacity(batteryCapacity);
        } else if (existing instanceof Product_Housing housing) {
            System.out.print("Nouvelle hauteur : ");
            housing.setHeight(Double.parseDouble(scanner.nextLine()));
            System.out.print("Nouveau poids : ");
            housing.setWeight(Double.parseDouble(scanner.nextLine()));
        }

        service.update(existing, id);
        System.out.println("Produit mis à jour !");
    }

}