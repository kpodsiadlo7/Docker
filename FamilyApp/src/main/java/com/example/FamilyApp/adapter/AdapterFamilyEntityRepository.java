package com.example.FamilyApp.adapter;

import com.example.FamilyApp.domain.FamilyEntity;

public interface AdapterFamilyEntityRepository {
    FamilyEntity findById(long familyId);

    FamilyEntity save(FamilyEntity familyEntity);

    boolean existsById(Long familyId);
}
