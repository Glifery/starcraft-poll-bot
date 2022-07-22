package com.glifery.starcraftpollbot.service;

import com.glifery.starcraftpollbot.config.property.PollProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PollService {

    private static final Set<DayOfWeek> PLAYABLE_DAYS_OF_WEEK = Set.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY
    );
    private final static List<String> COMMON_OPTIONS = List.of("20:00", "20:30", "21:00");

    private final PollProperties pollProperties;


    public List<String> getOptions(LocalDate localDate) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (PLAYABLE_DAYS_OF_WEEK.contains(dayOfWeek)) {
            return this.getOptions(dayOfWeek);
        }
        var forceSend = pollProperties.getForceSend();
        if (Boolean.TRUE.equals(forceSend)) {
            return COMMON_OPTIONS;
        }
        return Collections.emptyList();
    }

    private List<String> getOptions(DayOfWeek dayOfWeek) {
        DayOfWeek nextPlayingDay = this.getNextPlayingDay(dayOfWeek);
        String nextDayName = nextPlayingDay.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return Stream.concat(COMMON_OPTIONS.stream(), Stream.of(nextDayName))
                .collect(Collectors.toUnmodifiableList());
    }

    private DayOfWeek getNextPlayingDay(DayOfWeek currentPlayingDay) {
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> dayOfWeek.compareTo(currentPlayingDay) > 0)
                .filter(PLAYABLE_DAYS_OF_WEEK::contains)
                .findFirst()
                .orElse(DayOfWeek.MONDAY);
    }

}
