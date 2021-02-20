package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Room;

import java.util.List;

public interface RoomsRepository extends JpaRepository<Room,Long> {
    List<Room> findAllByNameAndCreator_Email(String name, String creatorEmail);
}
