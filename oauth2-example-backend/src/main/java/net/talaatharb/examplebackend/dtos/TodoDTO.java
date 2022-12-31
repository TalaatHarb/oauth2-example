package net.talaatharb.examplebackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.util.StringUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoDTO {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime dueDate;

    @JsonIgnore
    public boolean isValidToCreate() {
        return !StringUtils.isBlank(title);
    }
}
