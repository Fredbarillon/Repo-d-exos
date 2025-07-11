package entity;

import lombok.*;
import util.OperationStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Operation {
    int id;
    Double amount;
    OperationStatus status;
    int accountId;
}
