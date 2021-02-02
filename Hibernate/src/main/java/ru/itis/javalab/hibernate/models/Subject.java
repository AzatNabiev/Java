package ru.itis.javalab.hibernate.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Subject {
    private Long id;
    private String subjectName;
    private Mentor mentor;
}
