package com.example.FamilyApp.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FamilyMemberDto {
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("age")
    private int age;

    /**
     * below
     * variables that are automatically updated
     * */
    private String familyName;
    private Long familyId;

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    public void setFamilyId(final Long familyId) {
        this.familyId = familyId;
    }

    @Override
    public String toString() {
        return "FamilyMemberDto{" +
                "familyName='" + familyName + '\'' +
                '}';
    }
}
