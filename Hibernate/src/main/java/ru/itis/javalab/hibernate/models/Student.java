package ru.itis.javalab.hibernate.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "mentors")
@ToString(exclude = "mentors")
public class Student {
    private Long id;
    private String name;
    private List<Mentor> mentors;
}
