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

    public AssetsDto(String flat, boolean hasHouse, boolean hasCar, String carAge, boolean wasAbroad) {
        this.flat = flat;
        this.hasHouse = hasHouse;
        this.hasCar = hasCar;
        this.carAge = carAge;
        this.wasAbroad = wasAbroad;
    }
}
