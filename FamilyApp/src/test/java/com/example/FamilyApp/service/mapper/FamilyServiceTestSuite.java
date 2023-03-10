package com.example.FamilyApp.service.mapper;

import com.example.FamilyApp.FamilyAppApplication;
import com.example.FamilyApp.adapter.AdapterFamilyEntityRepository;
import com.example.FamilyApp.service.FamilyService;
import com.example.FamilyApp.web.dto.FamilyDto;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FamilyAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FamilyServiceTestSuite {

    @Autowired
    private FamilyService familyService;


    @Test
    @DisplayName("should throw IllegalStateException when we put family id which is not exist in db")
    void getFamilyWithMembersWithFamilyIdWhichIsNotExistInDb(){
        //given
        var mockRepository = mock(AdapterFamilyEntityRepository.class);
        when(mockRepository.existsById(anyLong())).thenReturn(false);
        // system under test
        var test = new FamilyService(mockRepository,null,null);
        //when
        var exception = catchThrowable(() -> test.getFamilyWithMembers(0L));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("id doesn't exist!");
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
