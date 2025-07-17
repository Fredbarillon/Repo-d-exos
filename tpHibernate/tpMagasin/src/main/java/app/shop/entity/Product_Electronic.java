package app.shop.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.Duration;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Product_Electronic extends Product {
    private Duration batteryCapacity;

    @Override
        public String toString() {
            return super.toString() +
                ", batteryDuration=" + batteryCapacity;
    }
}
