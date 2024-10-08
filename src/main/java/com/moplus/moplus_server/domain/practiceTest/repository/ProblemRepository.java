package com.moplus.moplus_server.domain.practiceTest.repository;

import com.moplus.moplus_server.domain.practiceTest.entity.Problem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    List<Problem> findAllByPracticeTestId(Long id);

    void deleteAllByPracticeTestId(Long id);

    Optional<Problem> findByProblemNumberAndPracticeTestId(String problemNumber, Long practiceTest_id);
}
