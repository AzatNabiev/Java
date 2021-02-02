package ru.itis.javalab.hibernate.models;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "subjects")
@EqualsAndHashCode(exclude = "subjects")
@Builder
public class Mentor {
    private Long id;
    private String name;
    private Set<Subject> subjects;
}
