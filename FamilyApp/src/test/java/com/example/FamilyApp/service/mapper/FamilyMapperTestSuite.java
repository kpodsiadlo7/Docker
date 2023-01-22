package com.example.FamilyApp.service.mapper;

import com.example.FamilyApp.adapter.AdapterFamilyEntityRepository;
import com.example.FamilyApp.domain.FamilyEntity;
import com.example.FamilyApp.service.Family;
import com.example.FamilyApp.service.FamilyMapper;
import com.example.FamilyApp.web.dto.FamilyDto;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FamilyMapperTestSuite {

    @Autowired
    private FamilyMapper familyMapper;
    @Autowired
    private AdapterFamilyEntityRepository adapterFamilyEntityRepository;

    @Test
    void testingMapToFamilyEntityFromFamilyWithEmptyFamilyMemberList() {
        // given
        Family family = new Family(
                "Family",
                7L,
                3,
                2,
                9,
                new ArrayList<>()
        );
        //when
        FamilyEntity familyEntity = familyMapper.mapToFamilyEntityFromFamily(family);
        //then
        Assertions.assertEquals("Family",familyEntity.getFamilyName());
        Assertions.assertEquals(7,familyEntity.getId());
        Assertions.assertEquals(3,familyEntity.getNrOfAdults());
        Assertions.assertEquals(2,familyEntity.getNrOfChildren());
        Assertions.assertEquals(9,familyEntity.getNrOfInfants());
    }

    @Test
    void testingMapToFamilyDtoFromFamilyWithFamilyMemberList(){
        //given
        List<FamilyMemberDto> familyMemberDtoList = createFamilyMemberDtoList();
        Family family = new Family(
                "Family",
                7L,
                3,
                2,
                9,
                familyMemberDtoList
        );
        //when
        FamilyDto familyDto = familyMapper.mapToFamilyDtoFromFamily(family);
        String familyName = familyDto.getFamilyName();
        //then
        Assertions.assertEquals(familyName,family.getFamilyName());
        Assertions.assertEquals(2,family.getFamilyMembersDto().size());
        Assertions.assertEquals(7L, familyDto.getFamilyId());
        Assertions.assertEquals(3, familyDto.getNrOfAdults());
        Assertions.assertEquals(2, familyDto.getNrOfChildren());
        Assertions.assertEquals(0, familyDto.getNrOfInfants());
    }


    @Test
    void testingMapToFamilyFromFamilyDtoWithFamilyMemberList_AfterThatWeCreateFamilyEntityWhichWeSaveInRepository(){
        //given
        List<FamilyMemberDto> familyMemberDtoList = createFamilyMemberDtoList();
        //and
        FamilyDto familyDto = new FamilyDto(
                "familyName",
                1,
                2,
                5,
                familyMemberDtoList,
                7L
        );
        //when
        Family family = familyMapper.mapToFamilyFromFamilyDto(familyDto);
        FamilyEntity familyEntity = familyMapper.mapToFamilyEntityFromFamily(family);
        FamilyMemberDto member1AfterMapFromFamilyDtoToFamily = (FamilyMemberDto) family.getFamilyMembersDto().stream()
                        .map(e -> e.getGivenName().equals("member1"));
        adapterFamilyEntityRepository.save(familyEntity);
        //then
        Assertions.assertEquals(1,familyEntity.getNrOfAdults());
        Assertions.assertEquals(2,familyEntity.getNrOfChildren());
        Assertions.assertEquals(5,familyEntity.getNrOfInfants());
        Assertions.assertEquals("member1",member1AfterMapFromFamilyDtoToFamily.getGivenName());
        Assertions.assertEquals(familyMemberDtoList.size(),family.getFamilyMembersDto().size());
    }


    private List<FamilyMemberDto> createFamilyMemberDtoList() {
        List<FamilyMemberDto> familyMemberDtoList = new ArrayList<>();
        FamilyMemberDto member1 = new FamilyMemberDto("member1",7);
        FamilyMemberDto member2 = new FamilyMemberDto("member2",42);
        familyMemberDtoList.add(member1);
        familyMemberDtoList.add(member2);
        return familyMemberDtoList;
    }
}

