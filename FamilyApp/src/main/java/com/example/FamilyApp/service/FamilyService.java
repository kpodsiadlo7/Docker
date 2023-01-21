package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.FamilyEntity;
import com.example.FamilyApp.repository.FamilyRepository;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class FamilyService {

    @Value("${RETURN_FAMILY_MEMBER_URL}")
    private String RETURN_FAMILY_MEMBERS_URL;
    @Value("${CREATE_FAMILY_MEMBER_URL}")
    private String CREATE_FAMILY_MEMBER_URL;
    @Value("${FAMILY.MEMBER.URL}")
    private String FAMILY_MEMBER_URL;
    private final FamilyRepository familyRepository;
    private final FamilyMapper familyMapper;
    private final RestTemplate restTemplate;

    FamilyService(final FamilyRepository familyRepository, final FamilyMapper familyMapper, final RestTemplate restTemplate) {
        this.familyRepository = familyRepository;
        this.familyMapper = familyMapper;
        this.restTemplate = restTemplate;
    }

    /*
        public FamilyDto getAllFamilyByFamilyId(final long familyId) {
            var familyMemberList = getDataFromAnotherDb();
            return familyMapper.mapToFamilyDto(familyRepository.findById(familyId), familyMemberList);
        }

        private Object getDataFromAnotherDb() {
            return null;
        }
     */
    private void sendDataToFamilyMemberController(final Family family, final Long familyId) {
        for (FamilyMemberDto dto : family.getFamilyMembersDto()) {
            dto.setFamilyName(family.getFamilyName());
            dto.setFamilyId(familyId);
        }
        for (FamilyMemberDto dto : family.getFamilyMembersDto()) {
            log.info(dto.getFamilyId().toString());
        }
        restTemplate.postForLocation(FAMILY_MEMBER_URL + CREATE_FAMILY_MEMBER_URL, family.getFamilyMembersDto());
    }


    public Long createFamily(final Family family) {
        FamilyEntity familyEntity = familyMapper.mapToFamilyEntityFromFamily(family);
        familyRepository.save(familyEntity);
        sendDataToFamilyMemberController(family, familyEntity.getId());
        return familyEntity.getId();
    }
}
