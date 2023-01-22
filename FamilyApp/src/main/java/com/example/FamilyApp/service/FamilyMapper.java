package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.FamilyEntity;
import com.example.FamilyApp.web.dto.FamilyDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
                familyDto.getFamilyId(),
                familyDto.getNrOfAdults(),
                familyDto.getNrOfChildren(),
                familyDto.getNrOfInfants(),
                familyDto.getFamilyMembersDto()
        );
    }

    public Family mapToFamilyFromFamilyEntity(final FamilyEntity familyEntity, final Long familyId) {
        return new Family(
                familyEntity.getFamilyName(),
                familyId,
                familyEntity.getNrOfAdults(),
                familyEntity.getNrOfChildren(),
                familyEntity.getNrOfInfants(),
                new ArrayList<>()
        );
    }

    public FamilyDto mapToFamilyDtoFromFamily(final Family family) {
        return new FamilyDto(
                family.getFamilyName(),
                family.getNrOfAdults(),
                family.getNrOfChildren(),
                family.getNrOfInfants(),
                family.getFamilyMembersDto(),
                family.getFamilyId()
        );
    }
}
