package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.FamilyEntity;
import com.example.FamilyApp.web.dto.FamilyDto;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyMapper {

    public FamilyEntity mapToFamilyEntityFromFamily(final Family family) {
        return new FamilyEntity(
                family.getFamilyName(),
                family.getNrOfAdults(),
                family.getNrOfChildren(),
                family.getNrOfInfants()
        );
    }

    public Family mapToFamilyFromFamilyDto(final FamilyDto familyDto) {
        return new Family(
                familyDto.getFamilyName(),
                familyDto.getNrOfAdults(),
                familyDto.getNrOfChildren(),
                familyDto.getNrOfInfants(),
                familyDto.getFamilyMembersDto()
        );
    }
}
