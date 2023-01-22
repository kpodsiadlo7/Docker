package com.example.FamilyApp.service;

import com.example.FamilyApp.repository.FamilyEntityRepository;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FamilyServiceTestSuite {

    @MockBean
    private FamilyMapper familyMapper;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private FamilyEntityRepository familyEntityRepository;

    @Test
    void updateFamilyNameAndFamilyIdInFamilyMemberDto() {
        //given
        List<FamilyMemberDto> familyMembers = new ArrayList<>();
        FamilyMemberDto member1 = new FamilyMemberDto();
        member1.setAge(11);
        member1.setGivenName("first member");
        FamilyMemberDto member2 = new FamilyMemberDto();
        member2.setAge(27);
        member2.setGivenName("second member");
        FamilyMemberDto member3 = new FamilyMemberDto();
        member3.setAge(20);
        member3.setGivenName("third member");

        familyMembers.add(member1);
        familyMembers.add(member2);
        familyMembers.add(member3);

        Family family = new Family(
                "Potter",
                0L,
                2,
                1,
                1,
                familyMembers
        );
        //when
    }
}

