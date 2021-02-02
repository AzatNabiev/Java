package ru.itis.javalab.hibernate.models;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"clients","types"})
@EqualsAndHashCode(exclude = {"clients","types"})
@Builder
public class Order {
    private Long id;
    private String name;
    private Set<Client> clients;
    private Set<Type> types;
}
