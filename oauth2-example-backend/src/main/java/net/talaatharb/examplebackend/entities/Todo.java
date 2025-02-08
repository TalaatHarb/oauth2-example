package net.talaatharb.examplebackend.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Todo extends BaseEntity{

    private String title;

    private String description;

    private LocalDateTime dueDate = LocalDateTime.now();

    private UUID userId;
}
