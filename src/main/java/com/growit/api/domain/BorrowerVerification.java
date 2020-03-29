package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "brwr_verification")
@EqualsAndHashCode(callSuper = false)
public class BorrowerVerification extends AbstractEntity {

    @OneToOne
    private Borrower borrower;

    @Column(name = "passport_lost_by_mvd") /* 1) */
    private boolean passportLostByMVD;

    @Column(name = "passport_verified_by_ubch") /* 1) */
    private boolean passportVerifiedByUBKH;

    private boolean employmentVerified;

    private boolean educationVerified; /**  ========  Add education obj + Доки УБКИ here ===========*/

    private boolean locationFetched;

    private boolean hasEmploymentBookFile;

    private boolean hasCertificateFromWorkFile;

    private boolean hasStatementOfIncomeFile;

    private boolean hasBankAccountStatement;

    private boolean employmentBookVerified;

    private boolean certificateFromWorkVerified;

    private boolean statementOfIncomeVerified;

    private boolean bankAccountStatementVerified;

    @Column(name = "itn_verified")
    private boolean ITNVerified;

    private boolean creditHistoryFound;

    /* Optional */
    private String employmentBookFile;

    /* Optional */
    private String certificateFromWorkFile;

    /* Optional */
    private String statementOfIncomeFile;

    /* Optional */
    private String bankAccountStatement;

    /* Optional */
    private String deviceLocation;

//    private double riskScore; // (consolidated, analysed)

/*    @Enumerated(EnumType.STRING)
    private SafetyClass safetyClass;*/

    private int GrowitReturnedCredits;

    private int GrowitDelayedCredits;

    private int GrowitDefaultedCredits;


}

/**
 1) Шаблоны отчетов для партнеров -> Проверка соответствия данных субъекта (ФИО+ДР+паспорт) и действительности документа.





 Доки УБКИ:

 Паспорт

 Водительское удостоверение


 Заграничный паспорт


 Свидетельство о рождении


 Удостоверение члена экипажа


 Военный билет

 Временное удостоверение личности

 Диплом

 Аттестат

 Паспорт иностранного гражданина

 Свидетельство про государственную регистрацию


 Выдержка из ЕГР


 Выписка из ЕГР


 Свидетельство про регистрацию плательщиков налогов

 Справка про взятие на учет плательщика налогов


 Вид на постоянное жительство (в том числе в виде ID-карты)

 Паспорт гражданина Украины в виде ID карты
 */
