package com.example.FamilyApp.service.mapper;

import com.example.FamilyApp.FamilyAppApplication;
import com.example.FamilyApp.adapter.AdapterFamilyEntityRepository;
import com.example.FamilyApp.domain.FamilyEntity;
import com.example.FamilyApp.service.Family;
import com.example.FamilyApp.service.FamilyMapper;
import com.example.FamilyApp.service.mapper.config.ContainersEnvironment;
import com.example.FamilyApp.web.dto.FamilyDto;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FamilyAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FamilyMapperTestSuite extends ContainersEnvironment {

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
        Assertions.assertEquals(9, familyDto.getNrOfInfants());
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

        adapterFamilyEntityRepository.save(familyEntity);
        //then
        Assertions.assertEquals(1,familyEntity.getNrOfAdults());
        Assertions.assertEquals(2,familyEntity.getNrOfChildren());
        Assertions.assertEquals(5,familyEntity.getNrOfInfants());
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

