package com.kenux.datetimetest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@ToString
public class TimeTestResponse {
    LocalDateTime seoulLocalDateTime;
    LocalDateTime sydneyLocalDateTime;

    ZonedDateTime seoulZonedDateTime;
    ZonedDateTime sydneyZonedDateTime;
}
