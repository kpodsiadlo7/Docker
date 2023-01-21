package com.example.FamilyMemberApp.web;

import com.example.FamilyMemberApp.service.FamilyMemberMapper;
import com.example.FamilyMemberApp.service.FamilyMemberService;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;
    private final FamilyMemberMapper memberMapper;

    FamilyMemberController(final FamilyMemberService familyMemberService, final FamilyMemberMapper memberMapper) {
        this.familyMemberService = familyMemberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping("/createfamilymember")
    public void createFamilyMember(@RequestBody final List<FamilyMemberDto> familyMemberDtoList) {
        familyMemberService.createFamilyMembers(memberMapper.mapToFamilyMemberListFromFamilyMemberDtoList(familyMemberDtoList));
    }

    @GetMapping("/getfamilymembers")
    public ResponseEntity<?> searchFamilyMember() {
        return ResponseEntity.ok().build();
    }
}
