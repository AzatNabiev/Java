package ru.itis.javalab.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Length(min=2, max=20)
    @Column(length = 256)
    private String firstName;
    @NotEmpty
    @Length(min=2, max=20)
    @Column(length = 256)
    private String lastName;
    @Min(value=18, message="must be equal or greater than 18")
    private Long age;
    @NotEmpty
    @Email
    @Length(min=6, max=40)
    @Column(length = 256)
    private String email;
    @NotEmpty
    @Length(min=8)
    @Column(length = 256)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany
    private Set<UserContent> userContent;
    @ManyToMany(mappedBy = "users")
    private Set<Room> rooms;
    @OneToMany(mappedBy = "author")
    private Set<Message> messages;
    @OneToMany(mappedBy = "creator")
    private Set<Room> createdRooms;
}
