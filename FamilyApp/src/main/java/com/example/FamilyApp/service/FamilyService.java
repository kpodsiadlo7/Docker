package com.example.FamilyApp.service;

import com.example.FamilyApp.domain.Family;
import com.example.FamilyApp.repository.FamilyRepository;
import com.example.FamilyApp.web.dto.FamilyDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {
    private final static String BASE_URL = "http://localhost:8000/";
    private final static String CREATE = "createfamilymember";
    private final static String RETURN = "returnfamilymembers";
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

    private void sendDataToFamilyMemberController(final FamilyDto familyDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FamilyDto> request = new HttpEntity<>(familyDto, headers);
        URI uri = restTemplate.postForLocation(BASE_URL + CREATE, request);
    }
 */

    public Long createFamily(final FamilyDto familyDto) {
        Family family = familyMapper.mapToFamily(familyDto);
        familyDto.setFamilyId(family.getId());
        //sendDataToFamilyMemberController(familyDto);
        familyRepository.save(family);
        return family.getId();
    }
    public String getMember() {
        URI url = UriComponentsBuilder.fromHttpUrl(BASE_URL+"string").build().encode().toUri();
        String s = restTemplate.getForObject(url, String.class);
        return s;
    }
}
