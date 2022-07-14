package com.glifery.starcraft_poll.application;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PollOptionsProvider {

    private static String NEXT_TOMORROW = "tomorrow";
    private static String NEXT_MONDAY = "monday";
    private static List<String> OPTIONS = Arrays.asList(
            "20:00",
            "20:30",
            "21:00"
    );

    Optional<List<String>> getOptions(Date date, Boolean forceSend) {
        List<String> options = new ArrayList<>(OPTIONS);
        if (forceSend.equals(true)) {
            return Optional.of(options);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case Calendar.MONDAY:
            case Calendar.TUESDAY:
                options.add(NEXT_TOMORROW);
                break;
            case Calendar.WEDNESDAY:
                options.add(NEXT_MONDAY);
                break;
            default:
                return Optional.empty();
        }

        return Optional.of(options);
    }
}
