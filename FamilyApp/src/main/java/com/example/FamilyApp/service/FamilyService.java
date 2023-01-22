package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.FamilyEntity;
import com.example.FamilyApp.repository.FamilyEntityRepository;
import com.example.FamilyApp.web.dto.FamilyDto;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyService {

    @Value("${RETURN.FAMILY.MEMBER.URL}")
    private String RETURN_FAMILY_MEMBERS_URL;
    @Value("${CREATE.FAMILY.MEMBER.URL}")
    private String CREATE_FAMILY_MEMBER_URL;
    @Value("${FAMILY.MEMBER.URL}")
    private String FAMILY_MEMBER_URL;
    private final FamilyEntityRepository familyEntityRepository;
    private final FamilyMapper familyMapper;
    private final RestTemplate restTemplate;

    private void sendDataToFamilyMemberController(final Family family, final Long familyId, String familyName) {
        for (FamilyMemberDto dto : family.getFamilyMembersDto()) {
            log.warn("Data to send");
            log.info("family given member: " + dto.getGivenName());
            log.info("family age member: " + dto.getAge());
        }
        restTemplate.postForLocation(FAMILY_MEMBER_URL + CREATE_FAMILY_MEMBER_URL + "/" + familyId + "/" + familyName, family.getFamilyMembersDto());
    }


    public Long createFamily(final Family family) {
        FamilyEntity familyEntity = familyMapper.mapToFamilyEntityFromFamily(family);
        familyEntityRepository.save(familyEntity);
        sendDataToFamilyMemberController(family, familyEntity.getId(), familyEntity.getFamilyName());
        return familyEntity.getId();
    }

    public FamilyDto getFamilyWithMembers(final Long familyId) {
        if (!familyEntityRepository.existsById(familyId))
            throw new IllegalStateException("Family with given id doesn't exist!");
        Family familyFromDb = familyMapper.mapToFamilyFromFamilyEntity(familyEntityRepository.findById(familyId.longValue()), familyId);
        FamilyMemberDto[] membersFromAnotherDb = restTemplate.getForObject(
                FAMILY_MEMBER_URL + RETURN_FAMILY_MEMBERS_URL + "/" + familyId, FamilyMemberDto[].class);
        return dataAggregation(familyFromDb, membersFromAnotherDb);
    }

    private FamilyDto dataAggregation(final Family family, final FamilyMemberDto[] familyMemberDtos) {
        for (FamilyMemberDto dto : familyMemberDtos)
            family.getFamilyMembersDto().add(dto);
        return familyMapper.mapToFamilyDtoFromFamily(family);
    }
}