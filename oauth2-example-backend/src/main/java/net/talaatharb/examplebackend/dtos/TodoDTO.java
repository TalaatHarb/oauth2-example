package net.talaatharb.examplebackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.util.StringUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TodoDTO extends BaseDTO{

    private String title;

    private String description;

    private LocalDateTime dueDate = LocalDateTime.now();

    private UUID userId;

    @JsonIgnore
    public boolean isValidToCreate() {
        return !StringUtils.isBlank(title);
    }
}
