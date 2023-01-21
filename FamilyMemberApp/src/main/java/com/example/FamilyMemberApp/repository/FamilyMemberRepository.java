package com.example.FamilyMemberApp.repository;

import com.example.FamilyMemberApp.domain.FamilyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity,Long> {
}
