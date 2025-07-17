package app.shop.entity;

import app.shop.util.Category;
import app.shop.util.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Product_Clothing extends Product {
    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Override
    public String toString() {
        return super.toString() +
                ", category = " + category.toString() +
                ", size = " + size.toString();
    }
}
