package com.moplus.moplus_server.domain.practiceTest.entity;

import com.moplus.moplus_server.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class PracticeTest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "practice_test_id")
    Long id;

    private String name;
    private String round;
    private String provider;
    private long viewCount = 0L;
    private int solvesCount = 0;
    private String publicationYear;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    private Duration averageSolvingTime = Duration.ZERO;

    @Builder
    public PracticeTest(String name, String round, String provider, String publicationYear, Subject subject) {
        this.name = name;
        this.round = round;
        this.provider = provider;
        this.viewCount = 0;
        this.solvesCount = 0;
        this.publicationYear = publicationYear;
        this.subject = subject;
        this.averageSolvingTime =  Duration.ZERO;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateRound(String round) {
        this.round = round;
    }

    public void updateProvider(String provider) {
        this.provider = provider;
    }

    public void updateSubject(Subject subject) {
        this.subject = subject;
    }

    public void updatePublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    public void plus1ViewCount() {
        this.viewCount += 1;
    }

    public void plus1SolvesCount() {
        this.solvesCount += 1;
    }

    public void updateAverageSolvingTime(Duration averageSolvingTime) {
        this.averageSolvingTime = averageSolvingTime;
    }
}
