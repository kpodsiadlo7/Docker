package com.example.FamilyApp.service.mapper;

import com.example.FamilyApp.service.Family;
import com.example.FamilyApp.service.FamilyService;
import com.example.FamilyApp.web.dto.FamilyDto;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FamilyServiceTestSuite {

    @Autowired
    private FamilyService familyService;

    @Test
    void testingDataAggregationMethod() {
        //given
        Family family = new Family(
                "Family",
                7L,
                3,
                2,
                9,
                new ArrayList<>()
        );
        FamilyMemberDto[] familyMemberDtos = new FamilyMemberDto[3];
        familyMemberDtos[0] = new FamilyMemberDto("Peter", 25);
        familyMemberDtos[1] = new FamilyMemberDto("Stevie", 2);
        familyMemberDtos[2] = new FamilyMemberDto("Brian", 7);
        //when
        Family familyAfterDataAggregation = familyService.dataAggregation(family, familyMemberDtos);
        int sizeShouldBe3 = familyAfterDataAggregation.getFamilyMembersDto().size();
        FamilyMemberDto shouldBeStevie = familyAfterDataAggregation.getFamilyMembersDto().stream().filter(
                familyMemberDto -> familyMemberDto.getGivenName().equals("Stevie")).findFirst().get();
        //then
        Assertions.assertEquals("Family", familyAfterDataAggregation.getFamilyName());
        Assertions.assertEquals(3, sizeShouldBe3);
        Assertions.assertEquals(2, shouldBeStevie.getAge());
    }

    @Test
    @DisplayName("should return false passing null or empty list")
    void testingValidateFamilyDataWithEmptyListAndNull() {
        //given
        FamilyDto familyDtoWithEmptyMembersDtoList = new FamilyDto(
                "familyName",
                1,
                2,
                5,
                List.of(),
                7L
        );
        FamilyDto familyDtoWithNullInsteadMembersList = new FamilyDto(
                "familyName",
                1,
                2,
                5,
                null,
                7L
        );
        //when&then
        Assertions.assertFalse(familyService.validateFamilyData(familyDtoWithEmptyMembersDtoList));
        Assertions.assertFalse(familyService.validateFamilyData(familyDtoWithNullInsteadMembersList));
    }

    @Test
    @DisplayName("should return false passing blank family name or null")
    void testingValidateFamilyDataWithBlankFamilyNameAndNull() {
        //given
        FamilyDto familyDtoWithBlankFamilyName = new FamilyDto(
                "",
                1,
                2,
                5,
                List.of(new FamilyMemberDto("Potter", 27)),
                7L
        );
        FamilyDto familyDtoWithNullInsteadFamilyName = new FamilyDto(
                null,
                1,
                2,
                5,
                List.of(new FamilyMemberDto("Potter", 27)),
                7L
        );
        //when&then
        Assertions.assertFalse(familyService.validateFamilyData(familyDtoWithBlankFamilyName));
        Assertions.assertFalse(familyService.validateFamilyData(familyDtoWithNullInsteadFamilyName));
    }

    @Test
    @DisplayName("should return false passing wrong number family members than pass data")
    void testingValidateFamilyDataWithIncorrectNumbersOfFamily() {
        //given
        FamilyDto familyDtoWithIncorrectNumbersThanData = new FamilyDto(
                "Nowak",
                1,
                2,
                5,
                List.of(new FamilyMemberDto("Potter", 27)),
                7L
        );
        //when&then
        Assertions.assertFalse(familyService.validateFamilyData(familyDtoWithIncorrectNumbersThanData));
    }

    @Test
    @DisplayName("should return true passing correct data")
    void testingValidateFamilyDataWithCorrectData() {
        //given
        FamilyDto familyDtoWithCorrectData = new FamilyDto(
                "Nowak",
                1,
                0,
                0,
                List.of(new FamilyMemberDto("Potter", 27)),
                7L
        );
        //when&then
        Assertions.assertTrue(familyService.validateFamilyData(familyDtoWithCorrectData));
    }
}
