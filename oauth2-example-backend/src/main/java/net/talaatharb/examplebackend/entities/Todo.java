package net.talaatharb.examplebackend.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Todo extends BaseEntity{

    private String title;

    private String description;

    private LocalDateTime dueDate = LocalDateTime.now();

    private UUID userId;
}
