package com.spring.bind;

import com.spring.validation.Event;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class EventEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {

        Event event = (Event) getValue();

        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event((Integer.parseInt(text))));
    }
}
