package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.FamilyMemberAppApplication;
import com.example.FamilyMemberApp.adapter.AdapterFamilyMemberRepository;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FamilyMemberAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FamilyMemberAppTestSuite {

    @Autowired
    private FamilyMemberMapper familyMemberMapper;
    @Autowired
    private AdapterFamilyMemberRepository adapterFamilyMemberRepository;
    @Autowired
    private FamilyMemberService familyMemberService;

    @Test
    @DisplayName("this method testing all of mappers")
    void testingCreateFamilyMemberMethodInService() {
        //given
        FamilyMember familyMember = createAndSaveInDbFamilyMember();
        //when
        int dbSizeBeforeCreateFamilyMembersMethod = adapterFamilyMemberRepository.findAll().size();
        familyMemberService.createFamilyMembers(familyMember);
        int dbSizeAfterCreateFamilyMembersMethod = adapterFamilyMemberRepository.findAll().size();
        //then
        Assertions.assertEquals(dbSizeBeforeCreateFamilyMembersMethod + 1, dbSizeAfterCreateFamilyMembersMethod);
    }


    @Test
    @DisplayName("should return all members by family id from db")
    void testingGetFamilyMembersByFamilyIdInService() {
        //given
        FamilyMember familyMemberAfterMapToFamilyMemberFromFamilyMemberDto = createAndSaveInDbFamilyMember();
        //when
        String shouldBeFamilyGuy = familyMemberAfterMapToFamilyMemberFromFamilyMemberDto.getFamilyName();
        //then
        Assertions.assertEquals("FamilyGuy", shouldBeFamilyGuy);
    }

    private FamilyMember createAndSaveInDbFamilyMember() {
        Long familyId = 7L;
        String familyName = "FamilyGuy";
        FamilyMemberDto familyMemberDto = new FamilyMemberDto("Dobre imie", 24);
        return familyMemberMapper.mapToFamilyMemberFromFamilyMemberDto(familyMemberDto, familyId, familyName);
    }
}
