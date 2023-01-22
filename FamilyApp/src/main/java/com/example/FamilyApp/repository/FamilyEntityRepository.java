package com.example.FamilyApp.repository;

import com.example.FamilyApp.domain.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyEntityRepository extends JpaRepository<FamilyEntity, Long> {
    FamilyEntity findById(long familyId);
}
