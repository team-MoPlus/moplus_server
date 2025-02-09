package com.moplus.moplus_server.domain.publish.controller;

import com.moplus.moplus_server.domain.publish.dto.request.PublishPostRequest;
import com.moplus.moplus_server.domain.publish.dto.response.PublishMonthGetResponse;
import com.moplus.moplus_server.domain.publish.service.PublishDeleteService;
import com.moplus.moplus_server.domain.publish.service.PublishGetService;
import com.moplus.moplus_server.domain.publish.service.PublishSaveService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/publish")
@RequiredArgsConstructor
public class PublishController {

    private final PublishGetService publishGetService;
    private final PublishSaveService publishSaveService;
    private final PublishDeleteService publishDeleteService;

    @GetMapping("/{year}/{month}")
    @Operation(summary = "연월별 발행 조회", description = "연월별로 발행된 세트들을 조회합니다.")
    public ResponseEntity<List<PublishMonthGetResponse>> getPublishMonth(
            @PathVariable int year,
            @PathVariable int month
    ) {
        return ResponseEntity.ok(publishGetService.getPublishMonth(year, month));
    }

    @PostMapping("")
    @Operation(summary = "발행 생성하기", description = "특정 날짜에 문항세트를 발행합니다.")
    public ResponseEntity<Long> postPublish(
            @RequestBody PublishPostRequest request
    ) {
        return ResponseEntity.ok(publishSaveService.createPublish(request));
    }

    @DeleteMapping("/{publishId}")
    @Operation(summary = "발행 삭제", description = "발행을 삭제합니다.")
    public ResponseEntity<Void> deleteProblemSet(
            @PathVariable Long publishId
    ) {
        publishDeleteService.deletePublish(publishId);
        return ResponseEntity.noContent().build();
    }
}
