package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ItnDto extends AbstractDto {

    private long userId;

    /** Identificator. "true" for investor, "false" for borrower */
    private boolean investor;
}
