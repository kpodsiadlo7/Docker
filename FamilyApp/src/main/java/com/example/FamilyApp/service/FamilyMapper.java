package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.Family;
import com.example.FamilyApp.web.dto.FamilyDto;
import org.springframework.stereotype.Service;

@Service
public class FamilyMapper {

    public Family mapToFamily(final FamilyDto familyDto) {
        return new Family(
                familyDto.getFamilyName(),
                familyDto.getNrOfAdults(),
                familyDto.getNrOfChildren(),
                familyDto.getNrOfInfants()
        );
    }
}
