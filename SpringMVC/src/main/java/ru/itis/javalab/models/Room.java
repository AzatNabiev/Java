package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"creator","users","messages"})
@EqualsAndHashCode(exclude = {"creator","users"})
@Entity
@Table(name = "user_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    private String name;
    @ManyToMany
    @JoinTable(name = "account_room",
            joinColumns = @JoinColumn(name="room_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    private Set<User> users;

    @OneToMany(mappedBy = "room")
    private Set<Message> messages;
}
