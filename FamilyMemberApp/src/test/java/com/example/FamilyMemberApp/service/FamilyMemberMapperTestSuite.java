package com.example.FamilyMemberApp.service;

import com.example.FamilyMemberApp.FamilyMemberAppApplication;
import com.example.FamilyMemberApp.domain.FamilyMemberEntity;
import com.example.FamilyMemberApp.web.dto.FamilyMemberDto;
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
@SpringBootTest(classes = FamilyMemberAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FamilyMemberMapperTestSuite {

    @Autowired
    private FamilyMemberMapper familyMemberMapper;

    @Test
    void testingMapToFamilyMemberEntityFromFamilyMember(){
        //given
        FamilyMember familyMember = new FamilyMember(
                7L,
                "Truck",
                "Truckersi",
                34);
        //when
        FamilyMemberEntity afterMapper = familyMemberMapper.mapToFamilyMemberEntityFromFamilyMember(familyMember);
        //then
        Assertions.assertEquals(7L,afterMapper.getFamilyId());
        Assertions.assertEquals("Truck",afterMapper.getGivenName());
        Assertions.assertEquals("Truckersi", afterMapper.getFamilyName());
        Assertions.assertEquals(34,afterMapper.getAge());
    }

    @Test
    void testingMapToFamilyMemberFromFamilyMemberDto(){
        //given
        FamilyMemberDto familyMemberDto = new FamilyMemberDto(
                7L,
                "givenName",
                "family name",
                23
        );
        //when
        FamilyMember afterMapper = familyMemberMapper.mapToFamilyMemberFromFamilyMemberDto(familyMemberDto,7L,"family name");
        //then
        Assertions.assertEquals(7L,afterMapper.getFamilyId());
        Assertions.assertEquals("givenName",afterMapper.getGivenName());
        Assertions.assertEquals("family name", afterMapper.getFamilyName());
        Assertions.assertEquals(23,afterMapper.getAge());
    }

    @Test
    void testMapToFamilyMemberDtoListFromFamilyMemberEntity(){
        //given
        List<FamilyMemberEntity> familyMemberEntityList = new ArrayList<>();
        FamilyMemberEntity member1 = new FamilyMemberEntity(
                7L,
                "patryk",
                "familyName",
                33
        );
        FamilyMemberEntity member2 = new FamilyMemberEntity(
                7L,
                "adam",
                "familyName",
                22
        );
        familyMemberEntityList.add(member1);
        familyMemberEntityList.add(member2);
        //when
        List<FamilyMemberDto> afterMapper = familyMemberMapper.mapToFamilyMemberDtoListFromFamilyMemberEntityList(familyMemberEntityList);
        FamilyMemberDto member2Dto = afterMapper.stream().filter(e -> e.getGivenName().equals("adam")).findFirst().get();
        //then
        Assertions.assertEquals(2,afterMapper.size());
        Assertions.assertNull(member2Dto.getFamilyId());
        Assertions.assertNull(member2Dto.getFamilyName());
        Assertions.assertEquals("adam",member2Dto.getGivenName());
        Assertions.assertEquals(22,member2Dto.getAge());
    }
}
