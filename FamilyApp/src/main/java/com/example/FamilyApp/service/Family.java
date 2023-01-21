package com.example.FamilyApp.service;

import com.example.FamilyApp.web.dto.FamilyMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Family {
    private String familyName;
    private int nrOfAdults;
    private int nrOfChildren;
    private int nrOfInfants;
    private List<FamilyMemberDto> familyMembersDto;
}
