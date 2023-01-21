package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.Family;
import com.example.FamilyApp.web.dto.FamilyDto;
import org.springframework.stereotype.Service;

@Service
public class FamilyMapper {
    public FamilyDto mapToFamilyDto(Family family){
        return new FamilyDto(
                family.getFamilyName(),
                "",
                family.getNrOfAdults(),
                family.getNrOfChildren(),
                family.getNrOfInfants(),
                0
        );
    }
}