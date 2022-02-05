package com.example.timezone;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void toUTC() {

        Time time = new Time("10:30:00", "+4");
        ResponseEntity<Object> newTime = Utils.toUTC(time);

        Object body = newTime.getBody();
        HashMap<?, ?> map = ((HashMap<?, ?>) body);

        Time timeTest = ((Time) map.get("response"));
        assertEquals("06:30:00", timeTest.time);
    }
}