package com.example.FamilyMemberApp.web;

import com.example.FamilyMemberApp.service.FamilyMemberMapper;
import com.example.FamilyMemberApp.service.FamilyMemberService;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;
    private final FamilyMemberMapper memberMapper;

    @PostMapping("/createfamilymember/{familyId}/{familyName}")
    public void createFamilyMember(@RequestBody final FamilyMemberDto familyMemberDto, @PathVariable Long familyId, @PathVariable String familyName) {
        familyMemberService.createFamilyMembers(memberMapper.mapToFamilyMemberFromFamilyMemberDto(familyMemberDto, familyId, familyName));
    }

    @GetMapping("/getfamilymembers/{familyId}")
    public ResponseEntity<List<FamilyMemberDto>> searchFamilyMember(@PathVariable Long familyId) {
        return ResponseEntity.ok(familyMemberService.getFamilyMembersByFamilyId(familyId));
    }
}
