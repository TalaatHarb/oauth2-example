package net.talaatharb.examplebackend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class BaseDTO {

    private Long id;

    private Instant creationDate;

    private Instant updateDate;
}
