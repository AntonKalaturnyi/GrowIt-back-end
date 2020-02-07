package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContactPersonDto extends AbstractDto {

    private String firstName;

    private String lastName;

    private String middleName;

    private String phone;

    /** On front-end: Lang: UA, ENG
     Allowed types: colleague; friend; relative*/
    private String relationship;

//    private long borrowerId;

    protected long itnId;

}
