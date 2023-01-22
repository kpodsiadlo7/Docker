package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.repository.FamilyMemberRepository;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FamilyMemberAppTestSuite {

    @Autowired
    private FamilyMemberMapper familyMemberMapper;
    @Autowired
    private FamilyMemberRepository familyMemberRepository;
    @Autowired
    private FamilyMemberService familyMemberService;

    @Test
    @DisplayName("this method testing all of mappers")
    void testingCreateFamilyMemberMethodInService() {
        //given
        FamilyMember familyMember = createAndSaveInDbFamilyMember();
        //when
        int dbSizeBeforeCreateFamilyMembersMethod = familyMemberRepository.findAll().size();
        familyMemberService.createFamilyMembers(familyMember);
        int dbSizeAfterCreateFamilyMembersMethod = familyMemberRepository.findAll().size();
        //then
        Assertions.assertEquals(dbSizeBeforeCreateFamilyMembersMethod + 1, dbSizeAfterCreateFamilyMembersMethod);
    }


    @Test
    @DisplayName("should return all members by family id from db")
    void testingGetFamilyMembersByFamilyIdInService(){
        //given
        FamilyMember familyMember = createAndSaveInDbFamilyMember();
        //when
        familyMemberService.createFamilyMembers(familyMember);
        List<FamilyMemberDto> familyMemberDto = familyMemberService.getFamilyMembersByFamilyId(7L);
        //and
        String shouldBeFamilyGuy = familyMemberDto.get(0).getFamilyName();
        Long shouldBe7 = familyMemberDto.get(0).getFamilyId();
        //then
        Assertions.assertEquals("FamilyGuy",shouldBeFamilyGuy);
        Assertions.assertEquals(7,shouldBe7);
    }
    private FamilyMember createAndSaveInDbFamilyMember() {
        Long familyId = 7L;
        String familyName = "FamilyGuy";
        FamilyMemberDto familyMemberDto = new FamilyMemberDto(
                "Dobre imie",
                24
        );
        return familyMemberMapper.mapToFamilyMemberFromFamilyMemberDto(familyMemberDto, familyId, familyName);
    }
}
