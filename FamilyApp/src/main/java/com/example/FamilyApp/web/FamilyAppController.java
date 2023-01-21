package com.example.FamilyApp.web;

import com.example.FamilyApp.service.FamilyService;
import com.example.FamilyApp.web.dto.FamilyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyAppController {
    private final FamilyService familyService;

    FamilyAppController(final FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/data")
    public ResponseEntity<FamilyDto> getFamilyById(@RequestParam Long familyId){
        return ResponseEntity.ok(familyService.getAllFamilyByFamilyId(familyId));
    }
    @GetMapping
    public String getString(){
        return "string";
    }
}
