package net.talaatharb.examplebackend.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Todo extends BaseEntity{

    private String title;

    private String description;

    private LocalDateTime dueDate;
}
