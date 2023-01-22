package com.example.FamilyMemberApp.repository;

import com.example.FamilyMemberApp.domain.FamilyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity, Long> {
    List<FamilyMemberEntity> findAllByFamilyId(Long familyId);
}
