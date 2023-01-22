package com.example.FamilyApp.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDto {
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("nrOfAdults")
    private int nrOfAdults;
    @JsonProperty("nrOfChildren")
    private int nrOfChildren;
    @JsonProperty("nrOfInfants")
    private int nrOfInfants;
    @JsonProperty("familyMembers")
    private List<FamilyMemberDto> familyMembersDto;

    /**
     * This variable is updated only when we use getFamily() for return to client
     */
    private Long familyId;
}
