package com.example.timezone.controllers;

import com.example.timezone.Time;
import com.example.timezone.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class TimeZoneController {

    @RequestMapping(value = "api/timezone", method = RequestMethod.POST)
    public ResponseEntity<Object> getTimeZone(@RequestBody Time time) {
        return Utils.toUTC(time);
    }
}
