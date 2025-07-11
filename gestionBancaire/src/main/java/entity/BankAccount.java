package entity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class BankAccount {
    private int id;
    private int customerId;
    List<Operation> operations;
    Double totalAmount;
}
