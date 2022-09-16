package com.spring.bind;

import com.spring.validation.Event;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

public class EventFormatter implements Formatter<Event> {

    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        return new Event(Integer.valueOf(text));
    }

    @Override
    public String print(Event object, Locale locale) {
        return object.getId().toString();
    }
}
