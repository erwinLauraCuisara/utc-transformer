package com.example.timezone;

public class Time {

    public String time;
    public String timezone;

    public Time(String time, String utc) {
        this.time = time;
        this.timezone = utc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


}
