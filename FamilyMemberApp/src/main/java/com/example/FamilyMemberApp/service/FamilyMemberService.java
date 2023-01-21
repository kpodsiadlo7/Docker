package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.repository.FamilyMemberRepository;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyMemberMapper familyMemberMapper;

    FamilyMemberService(final FamilyMemberRepository familyMemberRepository, final FamilyMemberMapper familyMemberMapper) {
        this.familyMemberRepository = familyMemberRepository;
        this.familyMemberMapper = familyMemberMapper;
    }

    public void createFamilyMembers(final List<FamilyMemberDto> familyMemberDtoList) {
        for (FamilyMemberDto dto: familyMemberDtoList){

        }
    }
}
