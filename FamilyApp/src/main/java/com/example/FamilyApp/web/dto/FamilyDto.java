package com.example.FamilyApp.web.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FamilyDto {
    private String familyName;
    private String givenName;
    private int nrOfAdults;
    private int nrOfChildren;
    private int nrOfInfants;
    private int age;
}
