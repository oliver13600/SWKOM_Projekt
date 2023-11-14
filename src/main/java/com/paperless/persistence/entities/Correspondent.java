package com.paperless.persistence.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Correspondent {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 256)
    private String match;

    @Column(nullable = false)
    private Integer matchingAlgorithm;

    @Column(nullable = false)
    private Boolean isInsensitive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "correspondent")
    private Set<Document> correspondentDocuments;

    @OneToMany(mappedBy = "assignCorrespondent")
    private Set<MailRule> assignCorrespondentMailRules;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(final String match) {
        this.match = match;
    }

    public Integer getMatchingAlgorithm() {
        return matchingAlgorithm;
    }

    public void setMatchingAlgorithm(final Integer matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
    }

    public Boolean getIsInsensitive() {
        return isInsensitive;
    }

    public void setIsInsensitive(final Boolean isInsensitive) {
        this.isInsensitive = isInsensitive;
    }

    public AuthUser getOwner() {
        return owner;
    }

    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }

    public Set<Document> getCorrespondentDocumentsDocuments() {
        return correspondentDocuments;
    }

    public void setCorrespondentDocumentsDocuments(
            final Set<Document> correspondentDocuments) {
        this.correspondentDocuments = correspondentDocuments;
    }

    public Set<MailRule> getAssignCorrespondentPaperlessMailMailrules() {
        return assignCorrespondentMailRules;
    }

    public void setAssignCorrespondentPaperlessMailMailrules(
            final Set<MailRule> assignCorrespondentMailRules) {
        this.assignCorrespondentMailRules = assignCorrespondentMailRules;
    }

}
