package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.FamilyEntity;
import com.example.FamilyApp.repository.FamilyRepository;
import com.example.FamilyApp.web.dto.FamilyMemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class FamilyService {

    @Value("${RETURN_FAMILY_MEMBER_URL}")
    private String getFamilyMembersUrl;
    @Value("${CREATE_FAMILY_MEMBER_URL}")
    private String createFamilyMemberUrl;
    @Value("${FAMILY.MEMBER.URL}")
    private String baseFamilyMemberUrl;
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
        restTemplate.postForLocation(baseFamilyMemberUrl+createFamilyMemberUrl,family.getFamilyMembersDto());
    }


    public Long createFamily(final Family family) {
        FamilyEntity familyEntity = familyMapper.mapToFamilyEntityFromFamily(family);
        familyRepository.save(familyEntity);
        sendDataToFamilyMemberController(family, familyEntity.getId());
        return familyEntity.getId();
    }
}
