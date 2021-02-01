package ru.itis.javalab.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @NotEmpty
    @Length(min=2, max=20)
    private String firstName;
    @NotEmpty
    @Length(min=2, max=20)
    private String lastName;
    @Min(value=18, message="must be equal or greater than 18")
    private Integer age;
    @NotEmpty
    @Email
    @Length(min=6, max=40)
    private String email;
    @NotEmpty
    @Length(min=8)
    private String password;
    private Integer role;
}
