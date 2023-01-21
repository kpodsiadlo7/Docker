package com.example.FamilyMemberApp.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyMember {
    private Long familyId;
    private String givenName;
    private String familyName;
    private int age;
}
