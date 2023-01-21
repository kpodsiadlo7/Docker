package com.example.FamilyApp.web;

import com.example.FamilyApp.service.FamilyMapper;
import com.example.FamilyApp.service.FamilyService;
import com.example.FamilyApp.web.dto.FamilyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class FamilyAppController {
    private final FamilyService familyService;
    private final FamilyMapper familyMapper;

    FamilyAppController(final FamilyService familyService, final FamilyMapper familyMapper) {
        this.familyService = familyService;
        this.familyMapper = familyMapper;
    }

    /*
    @GetMapping("/getfamily")
    public ResponseEntity<FamilyDto> getFamilyById(@RequestParam Long familyId){
        return ResponseEntity.ok(familyService.getAllFamilyByFamilyId(familyId));
    }

     */


    @PostMapping("/createfamily")
    public ResponseEntity<String> createFamily(@RequestBody FamilyDto familyDto) {
        log.info(familyDto.toString());
        return ResponseEntity.ok(String.format("Family nr: %d",
                familyService.createFamily(familyMapper.mapToFamilyFromFamilyDto(familyDto))));
    }
}
