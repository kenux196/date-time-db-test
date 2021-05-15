package com.kenux.datetimetest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
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

    @PostMapping
    public ResponseEntity<?> registerTime() {
        TimeTest time = createTime();
        TimeTest save = timeTestRepository.save(time);
        return ResponseEntity.ok("saved : " + save);
    }

    @GetMapping
    public ResponseEntity<?> getTimeInfo() {
        List<TimeTest> results = timeTestRepository.findAll();
        List<TimeTestResponse> responseList = new ArrayList<>();
        for (TimeTest result : results) {
//            ZonedDateTime seoulTime = result.getSeoulTime();
//            System.out.println("seoulTime = " + seoulTime);
//            ZonedDateTime sydneyTime = result.getSydneyTime();
//            System.out.println("sydneyTime = " + sydneyTime);
//            System.out.println("sydneyTime.getOffset() = " + sydneyTime.getOffset());

            TimeTestResponse timeTestResponse = TimeTestResponse.builder()
                    .seoulLocalDateTime(result.getSeoulLocalTime())
                    .sydneyLocalDateTime(result.getSydneyLocalTime())
                    .seoulZonedDateTime(result.getSeoulTime())
                    .sydneyZonedDateTime(result.getSydneyTime())
                    .build();
            responseList.add(timeTestResponse);
        }
        return ResponseEntity.ok(responseList);
    }

    private TimeTest createTime() {
        return new TimeTest(ZonedDateTime.now(seoulId), ZonedDateTime.now(sydneyId));
    }
}
