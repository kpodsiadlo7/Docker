package com.example.FamilyMemberApp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "family_member_db")
public class FamilyMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long familyId;
    private String givenName;
    private String familyName;
    private int age;

    public FamilyMemberEntity(final Long familyId, final String givenName, final String familyName, final int age) {
        this.familyId = familyId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.age = age;
    }
}
