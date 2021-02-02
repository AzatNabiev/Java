package ru.itis.javalab.hibernate.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Mark {
    private Long id;
    private Student student;
    private Subject subject;
    private Long mark;
    private Mentor mentor;
}
