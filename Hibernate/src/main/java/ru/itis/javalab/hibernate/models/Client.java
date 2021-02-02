package ru.itis.javalab.hibernate.models;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@Builder
public class Client {
    private Long id;
    private String name;
    private Set<Order> orders;
}
