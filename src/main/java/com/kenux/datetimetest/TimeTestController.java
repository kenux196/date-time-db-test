package com.kenux.datetimetest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/time-test")
public class TimeTestController {

    private final TimeTestRepository timeTestRepository;

    private final ZoneId sydneyId = ZoneId.of("Australia/Sydney");
    private final ZoneId seoulId = ZoneId.of("Asia/Seoul");

    private final ZoneOffset seoulOffset = ZoneOffset.ofHours(9);
    private final ZoneOffset sydneyOffset = ZoneOffset.ofHours(10);

    @PostMapping
    public ResponseEntity<?> registerTime() {
        TimeTest time = createTime();
        System.out.println("time = " + time);
        TimeTest save = timeTestRepository.save(time);
        return ResponseEntity.ok("saved : " + save);
    }

    @GetMapping
    public ResponseEntity<?> getTimeInfoWithConvertedZoneId() {
        List<TimeTest> results = getTimes();
        List<TimeTestResponse> responseList = new ArrayList<>();
        for (TimeTest result : results) {
            TimeTestResponse timeTestResponse = TimeTestResponse.builder()
                    .seoulLocalDateTime(result.getSeoulLocalTime())
                    .sydneyLocalDateTime(result.getSydneyLocalTime())
                    .seoulOffsetDateTime(result.getSeoulOffsetTime().withOffsetSameInstant(seoulOffset))
                    .sydneyOffsetDateTime(result.getSydneyOffsetTime().withOffsetSameInstant(sydneyOffset))
                    .seoulZonedDateTime(result.getSeoulTime().withZoneSameInstant(seoulId))
                    .sydneyZonedDateTime(result.getSydneyTime().withZoneSameInstant(sydneyId))
                    .build();
            responseList.add(timeTestResponse);
        }
        return ResponseEntity.ok(responseList);
    }

    private List<TimeTest> getTimes() {
        return timeTestRepository.findAll();
    }

    private TimeTest createTime() {
        return new TimeTest(ZonedDateTime.now(seoulId), ZonedDateTime.now(sydneyId));
    }
}
