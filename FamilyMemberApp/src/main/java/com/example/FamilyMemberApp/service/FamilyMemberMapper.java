package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.domain.FamilyMemberEntity;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyMemberMapper {
    public FamilyMemberEntity mapToFamilyMemberEntityFromFamilyMember(final FamilyMember familyMember) {
        return new FamilyMemberEntity(
                familyMember.getFamilyId(),
                familyMember.getGivenName(),
                familyMember.getFamilyName(),
                familyMember.getAge()
        );
    }

    public List<FamilyMemberDto> mapToFamilyMemberDtoListFromFamilyMemberEntityList(final List<FamilyMemberEntity> familyMemberEntities) {
        List<FamilyMemberDto> familyDtoMembers = new ArrayList<>();
        for (FamilyMemberEntity entity : familyMemberEntities) {
            FamilyMemberDto familyMemberDto = new FamilyMemberDto(
                    entity.getGivenName(),
                    entity.getAge()
            );
            familyDtoMembers.add(familyMemberDto);
        }
        return familyDtoMembers;
    }

    public FamilyMember mapToFamilyMemberFromFamilyMemberDto(final FamilyMemberDto familyMemberDto, final Long familyId, final String familyName) {
        return new FamilyMember(
                familyId,
                familyMemberDto.getGivenName(),
                familyName,
                familyMemberDto.getAge()
        );
    }
}
