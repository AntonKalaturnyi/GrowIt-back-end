package com.growit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegSectionsFilledFlagsDto extends AbstractDto {

    private boolean personalFilled;
    private boolean docsFilled;
    private boolean addressFilled;
    private boolean employmentFilled;
    private boolean educationFilled;
    private boolean assetsFilled;
}
