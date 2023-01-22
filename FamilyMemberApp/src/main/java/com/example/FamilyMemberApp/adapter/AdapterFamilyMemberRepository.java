package com.example.FamilyMemberApp.adapter;

import com.example.FamilyMemberApp.domain.FamilyMemberEntity;

import java.util.List;

public interface AdapterFamilyMemberRepository {
    List<FamilyMemberEntity> findAllByFamilyId(Long familyId);
    FamilyMemberEntity save(FamilyMemberEntity entity);
    List<FamilyMemberEntity> findAll();
}
