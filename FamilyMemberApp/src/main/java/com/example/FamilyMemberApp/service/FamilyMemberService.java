package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.repository.FamilyMemberRepository;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyMemberMapper familyMemberMapper;

    public void createFamilyMembers(final List<FamilyMember> familyMemberList) {
        for (FamilyMember member : familyMemberList) {
            familyMemberRepository.save(familyMemberMapper.mapToFamilyMemberEntityFromFamilyMember(member));
            log.info(member.getFamilyId().toString());
        }
    }

    public List<FamilyMemberDto> getFamilyMembersByFamilyId(final Long familyId) {
        return familyMemberMapper.mapToFamilyMemberDtoListFromFamilyMemberEntity(
                familyMemberRepository.findAllByFamilyId(familyId));
    }
}
