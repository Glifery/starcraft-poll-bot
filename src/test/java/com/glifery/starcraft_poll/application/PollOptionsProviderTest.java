package com.glifery.starcraft_poll.application;

import com.glifery.starcraft_poll.application.PollOptionsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PollOptionsProviderTest {
    PollOptionsProvider provider;

    @BeforeEach
    public void setup() {
        provider = new PollOptionsProvider();
    }

    @Test
    public void shouldReturnEmpty() throws ParseException {
        Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2022-07-15");
        Optional<List<String>> options = provider.getOptions(date, false);
        Assertions.assertEquals(Optional.empty(), options);
    }

    @Test
    public void shouldReturnNotEmptyForced() throws ParseException {
        Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2022-07-15");
        Optional<List<String>> options = provider.getOptions(date, true);
        Assertions.assertEquals(3, options.get().size());
    }

    @Test
    public void shouldReturnOptionTomorrowOnMon() throws ParseException {
        Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2022-07-11");
        Optional<List<String>> options = provider.getOptions(date, false);
        Assertions.assertEquals(4, options.get().size());
        Assertions.assertEquals("tomorrow", options.get().get(3));
    }

    @Test
    public void shouldReturnOptionTomorrowOnTue() throws ParseException {
        Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2022-07-12");
        Optional<List<String>> options = provider.getOptions(date, false);
        Assertions.assertEquals(4, options.get().size());
        Assertions.assertEquals("tomorrow", options.get().get(3));
    }

    @Test
    public void shouldReturnOptionMondayOnWed() throws ParseException {
        Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2022-07-13");
        Optional<List<String>> options = provider.getOptions(date, false);
        Assertions.assertEquals(4, options.get().size());
        Assertions.assertEquals("monday", options.get().get(3));
    }
}
