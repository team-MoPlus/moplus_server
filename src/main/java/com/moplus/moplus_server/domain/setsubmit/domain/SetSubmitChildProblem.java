package com.moplus.moplus_server.domain.setsubmit.domain;

import com.moplus.moplus_server.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetSubmitChildProblem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_submit_child_problem_id")
    private Long id;

    @Column(name = "set_submit_problem_id", nullable = false)
    private Long setSubmitProblemId;

    @Column(name = "child_problem_id", nullable = false)
    private Long childProblemId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubmitStatus status;

    @Builder
    public SetSubmitChildProblem(Long setSubmitProblemId, Long childProblemId, SubmitStatus status) {
        this.setSubmitProblemId = setSubmitProblemId;
        this.childProblemId = childProblemId;
        this.status = status;
    }
}
