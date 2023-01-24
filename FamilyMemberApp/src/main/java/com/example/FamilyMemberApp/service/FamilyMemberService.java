package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.adapter.AdapterFamilyMemberRepository;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyMemberService {

    private final AdapterFamilyMemberRepository adapterFamilyMemberRepository;
    private final FamilyMemberMapper familyMemberMapper;

    public void createFamilyMembers(final FamilyMember familyMember) {
        adapterFamilyMemberRepository.save(familyMemberMapper.mapToFamilyMemberEntityFromFamilyMember(familyMember));
    }

    public List<FamilyMemberDto> getFamilyMembersByFamilyId(final Long familyId) {
        return familyMemberMapper.mapToFamilyMemberDtoListFromFamilyMemberEntityList(
                adapterFamilyMemberRepository.findAllByFamilyId(familyId));
    }
}
