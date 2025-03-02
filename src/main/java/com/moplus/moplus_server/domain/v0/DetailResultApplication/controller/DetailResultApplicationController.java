package com.moplus.moplus_server.domain.v0.DetailResultApplication.controller;

import com.moplus.moplus_server.domain.v0.DetailResultApplication.dto.request.DetailResultApplicationPostRequest;
import com.moplus.moplus_server.domain.v0.DetailResultApplication.dto.response.ReviewNoteGetResponse;
import com.moplus.moplus_server.domain.v0.DetailResultApplication.service.DetailResultApplicationService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/detailResultApplication")
public class DetailResultApplicationController {

    private final DetailResultApplicationService detailResultApplicationService;

    @PostMapping("")
    @Operation(summary = "모의고사 결과 상세 분석서 신청하기")
    public ResponseEntity<ReviewNoteGetResponse> createApplication(
            @RequestBody DetailResultApplicationPostRequest request) {
        detailResultApplicationService.saveApplication(request);
        ReviewNoteGetResponse reviewNoteInfo = detailResultApplicationService.getReviewNoteInfo(request.testResultId());
        return ResponseEntity.ok(reviewNoteInfo);
    }

}
