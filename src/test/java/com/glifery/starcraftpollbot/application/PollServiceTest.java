package com.glifery.starcraftpollbot.application;

import com.glifery.starcraftpollbot.config.property.PollProperties;
import com.glifery.starcraftpollbot.service.PollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PollServiceTest {

    private static final List<String> WEDNESDAY_OPTIONS = List.of("20:00", "20:30", "21:00", "Monday");

    @Mock
    private PollProperties pollProperties;
    @InjectMocks
    private PollService pollService;

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, names = {"MONDAY", "TUESDAY", "WEDNESDAY"})
    void getOptions_shouldReturnOptions_whenGameDay(DayOfWeek dayOfWeek) {
        //given
        //when
        List<String> actual = pollService.getOptions(dayOfWeek);
        //then
        assertFalse(actual.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, names = {"THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"})
    void getOptions_shouldReturnEmpty_whenNotGameDay(DayOfWeek dayOfWeek) {
        //given
        //when
        List<String> actual = pollService.getOptions(dayOfWeek);
        //then
        assertTrue(actual.isEmpty());
    }

    @Test
    void getOptions_shouldReturnOptions_whenForceSend() {
        //given
        doReturn(Boolean.TRUE).when(pollProperties).getForceSend();
        //when
        List<String> actual = pollService.getOptions(DayOfWeek.THURSDAY);
        //then
        assertFalse(actual.isEmpty());
    }

    @Test
    void getOptions_shouldReturnMondayOption_whenWednesday() {
        //given
        //when
        List<String> actual = pollService.getOptions(DayOfWeek.WEDNESDAY);
        //then
        assertEquals(WEDNESDAY_OPTIONS, actual);
    }

}
