package com.example.FamilyApp.repository;

import com.example.FamilyApp.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    Family findById(long familyId);
}
