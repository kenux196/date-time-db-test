package com.kenux.datetimetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@DataJpaTest
class TimeTestRepositoryTest {

    @Autowired TimeTestRepository timeTestRepository;

    final ZoneId sydneyId = ZoneId.of("Australia/Sydney");
    final ZoneId seoulId = ZoneId.of("Asia/Seoul");

    @Test
    void insertTime() {

        ZonedDateTime seoul = ZonedDateTime.now(seoulId);
        ZonedDateTime sydney = ZonedDateTime.now(sydneyId);

        TimeTest timeTest = new TimeTest(seoul, sydney);

        System.out.println("timeTest = " + timeTest);
        TimeTest save = timeTestRepository.save(timeTest);
        System.out.println("save = " + save);
    }
}