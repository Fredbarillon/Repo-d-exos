package entity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
}
