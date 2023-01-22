package com.example.FamilyApp.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyMemberDto {
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("age")
    private int age;
}
