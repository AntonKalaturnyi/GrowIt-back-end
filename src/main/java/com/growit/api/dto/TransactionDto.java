package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransactionDto extends AbstractDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    public LocalDateTime date;

    private double amount;

    private double commission;

    private String status;

    private String type;

    private String description;

    private double previousBalance;

}
