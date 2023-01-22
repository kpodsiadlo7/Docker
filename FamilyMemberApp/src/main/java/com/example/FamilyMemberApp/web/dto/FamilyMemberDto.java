package com.example.FamilyMemberApp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMemberDto {
    private Long familyId;
    private String givenName;
    private String familyName;
    private int age;

    public FamilyMemberDto(final String givenName, final int age) {
        this.givenName = givenName;
        this.age = age;
    }
}
