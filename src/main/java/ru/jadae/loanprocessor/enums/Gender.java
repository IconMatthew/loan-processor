package ru.jadae.loanprocessor.enums;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE");

    private final String gender;

    Gender(String genderTxt) {
        this.gender = genderTxt;
    }
}
