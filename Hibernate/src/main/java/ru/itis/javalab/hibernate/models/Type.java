package ru.itis.javalab.hibernate.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "type")
@EqualsAndHashCode(exclude = "type")
@Builder
public class Type {
    private Long id;
    private String name;
    private Order type;
}
