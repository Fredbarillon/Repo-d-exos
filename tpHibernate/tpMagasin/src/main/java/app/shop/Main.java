package app.shop;

import app.shop.entity.*;
import app.shop.service.CustomerService;
import app.shop.service.ProductService;
import app.shop.service.SaleService;
import app.shop.util.Category;
import app.shop.util.SaleStatus;
import app.shop.util.Size;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        SaleService saleService = new SaleService();

        Customer customer = Customer.builder()
                .firstName("Mister")
                .lastName("Martin")
                .email("mister.martin@proof.com")
                .build();
        customerService.createOrUpdate(customer);

        Product_Food food = Product_Food.builder()
                .description("Yaourt")
                .price(1.99)
                .stock(100)
                .restockDate(LocalDate.now().plusDays(15))
                .expiryDate(LocalDate.now().plusMonths(1))
                .build();

        Product_Electronic electronic = Product_Electronic.builder()
                .description("Casque")
                .price(49.26)
                .stock(30)
                .restockDate(LocalDate.now().plusWeeks(1))
                .batteryCapacity(Duration.ofHours(12))
                .build();

        Product_Clothing clothing = Product_Clothing.builder()
                .description("Jean")
                .price(39.00)
                .stock(50)
                .restockDate(LocalDate.now().plusDays(10))
                .category(Category.WOMEN)
                .size(Size.M)
                .build();

        productService.createOrUpdate(food);
        productService.createOrUpdate(electronic);
        productService.createOrUpdate(clothing);

        Sale sale = Sale.builder()
                .customer(customer)
                .date(LocalDate.now())
                .saleStatus(SaleStatus.FINALIZED)
                .productsSold(List.of(food, electronic, clothing))
                .saleTotal(food.getPrice() + electronic.getPrice() + clothing.getPrice())
                .build();

        saleService.createOrUpdate(sale);

        System.out.println("Client:");
        customerService.getAll().forEach(System.out::println);

        System.out.println("Produit:");
        productService.getAll().forEach(System.out::println);

        System.out.println("Vente:");
        saleService.getAll().forEach(System.out::println);
    }
}