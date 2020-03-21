package com.growit.api.dto;

public interface BorrowerTransferObject {

    int getMonthlyIncomeOfficial();
    int getMonthlyIncomeAdditional();
    boolean isMarried();
    int getKidsBefore18yo();
    int getKidsAfter18yo();
    String getSpouseITN();
    String getInstagram();
    String getFacebook();
    String getWorkType();
    String getEDRPOUcode();
    String getJobTitle();

}
