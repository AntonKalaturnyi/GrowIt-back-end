package com.growit.api.dto;

public interface BorrowerTransferObject {

    int getMonthlyIncomeOfficial();
    int getMonthlyIncomeAdditional();
    boolean isMarried();
    boolean isDivorced();
    int getKidsBefore18yo();
    int getKidsAfter18yo();
    String getSpouseITN();
    String getHomePhone();
    String getWorkPhone();
    String getFax();
    String getInstagram();
    String getFacebook();
    String getWorkType();
    String getEDRPOUcode();
    String getJobTitle();

}
