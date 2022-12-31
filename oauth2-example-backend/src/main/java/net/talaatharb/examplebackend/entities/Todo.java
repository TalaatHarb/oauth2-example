package net.talaatharb.examplebackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Todo extends BaseEntity{

    private String title;

    private String description;

    private LocalDateTime dueDate;
}
