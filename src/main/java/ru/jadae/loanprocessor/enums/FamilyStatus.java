package ru.jadae.loanprocessor.enums;

public enum FamilyStatus {

    SINGLE("SINGLE"),
    MARRIED("MARRIED"),
    DIVORCED("DIVORCED");

    private final String familyStatus;

    FamilyStatus(String familyStatusTxt) {
        this.familyStatus = familyStatusTxt;
    }
}
