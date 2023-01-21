package com.example.FamilyApp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FamilyMember {
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("age")
    private int age;
}
