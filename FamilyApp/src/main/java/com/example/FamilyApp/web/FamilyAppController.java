package com.example.FamilyApp.web;

import com.example.FamilyApp.service.FamilyMapper;
import com.example.FamilyApp.service.FamilyService;
import com.example.FamilyApp.web.dto.FamilyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class FamilyAppController {
    private final FamilyService familyService;
    private final FamilyMapper familyMapper;

    @GetMapping("/getfamily")
    public ResponseEntity<FamilyDto> getFamily(@RequestParam Long familyId) {
        return ResponseEntity.ok(familyMapper.mapToFamilyDtoFromFamily(familyService.getFamilyWithMembers(familyId)));
    }

    @Transactional
    @PostMapping("/createfamily")
    public ResponseEntity<String> createFamily(@RequestBody FamilyDto familyDto) {
        if (!familyService.validateFamilyData(familyDto))
            return ResponseEntity.badRequest().body("Incorrect declared quantity of family members with data which you send, " +
                                                    "members list or family name is empty");
        return ResponseEntity.ok(String.format("Family nr: %d",
                familyService.createFamily(familyMapper.mapToFamilyFromFamilyDto(familyDto))));
    }
}
