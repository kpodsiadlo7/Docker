package com.example.FamilyMemberApp.domain.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FamilyMemberController {

    @PostMapping("/createfamilymember")
    public void createFamilyMember(List<Object> source){
        System.out.println(source.get(0));
    }

    @GetMapping("/string")
    public String elo(){
        return "udało się";
    }
}
