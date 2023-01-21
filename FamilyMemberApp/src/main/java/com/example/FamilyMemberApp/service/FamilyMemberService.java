package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.repository.FamilyMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyMemberMapper familyMemberMapper;

    FamilyMemberService(final FamilyMemberRepository familyMemberRepository, final FamilyMemberMapper familyMemberMapper) {
        this.familyMemberRepository = familyMemberRepository;
        this.familyMemberMapper = familyMemberMapper;
    }

    public void createFamilyMembers(final List<FamilyMember> familyMemberList) {
        for (FamilyMember member : familyMemberList) {
            familyMemberRepository.save(familyMemberMapper.mapToFamilyMemberEntityFromFamilyMember(member));
            log.info(member.getFamilyId().toString());
        }
    }
}
