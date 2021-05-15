package com.kenux.datetimetest;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "time_test")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@ToString
public class TimeTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seoul_time")
    private ZonedDateTime seoulTime;

    @Column(name = "sydney_time")
    private ZonedDateTime sydneyTime;

    @Column(name = "seoul_offset_time")
    private OffsetDateTime seoulOffsetTime;

    @Column(name = "sydney_offset_time")
    private OffsetDateTime sydneyOffsetTime;

    @Column(name = "seoul_local_time")
    private LocalDateTime seoulLocalTime;

    @Column(name = "sydney_local_time")
    private LocalDateTime sydneyLocalTime;


    public TimeTest(ZonedDateTime seoulTime, ZonedDateTime sydneyTime) {
        this.seoulLocalTime = seoulTime.toLocalDateTime();
        this.sydneyLocalTime = sydneyTime.toLocalDateTime();
        this.seoulOffsetTime = seoulTime.toOffsetDateTime();
        this.sydneyOffsetTime = sydneyTime.toOffsetDateTime();
        this.seoulTime = seoulTime;
        this.sydneyTime = sydneyTime;
    }
}
