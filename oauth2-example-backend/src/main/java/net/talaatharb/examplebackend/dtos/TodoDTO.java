package net.talaatharb.examplebackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import io.micrometer.common.util.StringUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TodoDTO extends BaseDTO{

    @JsonView(JsonViewLevel.Summary.class)
    private String title;

    @JsonView(JsonViewLevel.Details.class)
    private String description;

    @JsonView(JsonViewLevel.Summary.class)
    private LocalDateTime dueDate = LocalDateTime.now();

    @JsonView(JsonViewLevel.Summary.class)
    private UUID userId;

    @JsonIgnore
    public boolean isValidToCreate() {
        return !StringUtils.isBlank(title);
    }
}
