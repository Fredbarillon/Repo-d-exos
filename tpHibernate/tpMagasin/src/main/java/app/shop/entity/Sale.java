package app.shop.entity;

import app.shop.util.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productsSold;

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    private double saleTotal;

    @Override
    public String toString() {
        return "Sale{id=" + id +
                ", customerId=" + (customer != null ? customer.getId() : "null") +
                ", date=" + date +
                ", status=" + saleStatus +
                ", total=" + saleTotal +
                '}';
    }

}
