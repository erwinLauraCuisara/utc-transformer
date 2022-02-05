package com.example.timezone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class Utils {

    public static ResponseEntity<Object> toUTC(Time time) {

        boolean isTimeCorrectFormat;
        boolean isTimezoneCorrect;
        try {
            isTimeCorrectFormat = time.time.matches("([01][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]");
            isTimezoneCorrect = time.timezone.matches("[-+]([1-9]|1[0-2])");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error check format");
        }

        if (!isTimeCorrectFormat)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error time format, use hh:mm:ss");
        else if (!isTimezoneCorrect)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error timezone format, use (+,-) in start");
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(formatTime(time));

    }


    public static HashMap<String, Time> formatTime(Time time) {
        List<String> timeList = new ArrayList<>(Arrays.asList(time.time.split(":")));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, toInt(timeList.get(0)));
        calendar.set(Calendar.MINUTE, toInt(timeList.get(1)));
        calendar.set(Calendar.SECOND, toInt(timeList.get(2)));

        calendar.add(Calendar.HOUR_OF_DAY, -(toInt(time.timezone)));

        String hour = formatCero(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = formatCero(calendar.get(Calendar.MINUTE));
        String seconds = formatCero(calendar.get(Calendar.SECOND));
        String newTime = hour + ":" + minute + ":" + seconds;

        HashMap<String, Time> map = new HashMap<>();
        map.put("response", new Time(newTime, "UTC"));

        return map;
    }


    public static String formatCero(int number) {
        String res = number + "";
        if (res.length() == 1) {
            res = "0" + res;
        }
        return res;
    }

    public static Integer toInt(String number) {
        return Integer.parseInt(number);
    }

}
