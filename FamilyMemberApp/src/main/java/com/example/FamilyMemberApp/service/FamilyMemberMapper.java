package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.domain.FamilyMemberEntity;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberMapper {
    public FamilyMemberEntity mapToFamilyMemberEntityListFromFamilyMemberDtoList(final FamilyMemberDto familyMemberDto) {
        return new FamilyMemberEntity(
                familyMemberDto.getFamilyId(),
                familyMemberDto.getGivenName(),
                familyMemberDto.getFamilyName(),
                familyMemberDto.getAge()
        );
    }
}
