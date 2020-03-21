package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AssetsDto extends AbstractDto {

    private String flat;
    private boolean hasHouse;
    private boolean hasCar;
    private String carAge;
    private boolean wasAbroad;
}
