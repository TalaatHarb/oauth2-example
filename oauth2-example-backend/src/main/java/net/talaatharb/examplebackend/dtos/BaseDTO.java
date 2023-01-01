package net.talaatharb.examplebackend.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.time.Instant;

@Data
public class BaseDTO {

    @JsonView(JsonViewLevel.Summary.class)
    private Long id;

    @JsonView(JsonViewLevel.Summary.class)
    private Instant creationDate;

    @JsonView(JsonViewLevel.Summary.class)
    private Instant updateDate;
}
