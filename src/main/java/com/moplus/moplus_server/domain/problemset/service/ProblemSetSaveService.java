package com.moplus.moplus_server.domain.problemset.service;

import com.moplus.moplus_server.domain.problem.domain.problem.ProblemAdminId;
import com.moplus.moplus_server.domain.problem.repository.ProblemRepository;
import com.moplus.moplus_server.domain.problemset.domain.ProblemSet;
import com.moplus.moplus_server.domain.problemset.dto.request.ProblemSetPostRequest;
import com.moplus.moplus_server.domain.problemset.repository.ProblemSetRepository;
import com.moplus.moplus_server.global.error.exception.ErrorCode;
import com.moplus.moplus_server.global.error.exception.InvalidValueException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProblemSetSaveService {

    private final ProblemSetRepository problemSetRepository;
    private final ProblemRepository problemRepository;

    @Transactional
    public Long createProblemSet(ProblemSetPostRequest request) {
        // 빈 문항 유효성 검증
        if (request.problems().isEmpty()) {
            throw new InvalidValueException(ErrorCode.EMPTY_PROBLEMS_ERROR);
        }

        // 문제 ID 리스트를 ProblemId 객체로 변환
        List<ProblemAdminId> problemAdminIdList = request.problems().stream()
                .map(ProblemAdminId::new)
                .toList();

        // 모든 문항이 DB에 존재하는지 검증
        problemAdminIdList.forEach(problemRepository::findByIdElseThrow);

        // ProblemSet 생성
        ProblemSet problemSet = request.toEntity(problemAdminIdList);

        return problemSetRepository.save(problemSet).getId();
    }

}
