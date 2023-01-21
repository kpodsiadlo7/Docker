package com.example.FamilyApp.web;

import com.example.FamilyApp.service.FamilyService;
import com.example.FamilyApp.web.dto.FamilyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class FamilyAppController {
    private final FamilyService familyService;

    FamilyAppController(final FamilyService familyService) {
        this.familyService = familyService;
    }

    /*
    @GetMapping("/getfamily")
    public ResponseEntity<FamilyDto> getFamilyById(@RequestParam Long familyId){
        return ResponseEntity.ok(familyService.getAllFamilyByFamilyId(familyId));
    }

     */
    @PostMapping("/createfamily")
    public ResponseEntity<Long> createFamily(@RequestBody FamilyDto familyDto) {
        System.out.println("family name: " + familyDto.getFamilyName());
        System.out.println("adults: " + familyDto.getNrOfAdults());
        System.out.println("infatns: " + familyDto.getNrOfInfants());
        System.out.println("children: " + familyDto.getNrOfChildren());
        System.out.println("first member" + familyDto.getFamilyMembers().get(0));
        return ResponseEntity.ok(familyService.createFamily(familyDto));
    }

    @GetMapping("/get")
    public String getSomething() {
        return familyService.getMember();
    }
}
