package com.growit.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "employment")
@EqualsAndHashCode(callSuper = false)
public class Employment extends AbstractEntity {

    private String nameOfCompany;

    private String EDRPOUcode;

    @ManyToOne
    @JoinColumn(name = "work_sphere_id")
    private WorkSphere workSphere;

    @Size(max = 50)
    @Column(name = "boss_first_name", length = 50)
    private String bossFirstName;

    @Size(max = 50)
    @Column(name = "boss_last_name", length = 50)
    private String bossLastName;

    @Size(max = 50)
    @Column(name = "boss_middle_name", length = 50)
    private String bossMiddleName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address jobAddress;

    private int lengthOfTotalEmploymentMo;  // In months

    private int lengthOfCurrentEmploymentMo;  // In months

    private int employerCount;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime nextPaymentDate;

    private String paymentFrequency;


//    private int numberOfEmployees;

//    private String priority;

}
