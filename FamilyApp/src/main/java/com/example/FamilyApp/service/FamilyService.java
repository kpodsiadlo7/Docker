package com.example.FamilyApp.service;

import com.example.FamilyApp.repository.FamilyRepository;
import com.example.FamilyApp.web.dto.FamilyDto;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final FamilyMapper familyMapper;

    FamilyService(final FamilyRepository familyRepository, final FamilyMapper familyMapper) {
        this.familyRepository = familyRepository;
        this.familyMapper = familyMapper;
    }

    public FamilyDto getAllFamilyByFamilyId(final long familyId) {
        return familyMapper.mapToFamilyDto(familyRepository.findById(familyId));
    }
}
