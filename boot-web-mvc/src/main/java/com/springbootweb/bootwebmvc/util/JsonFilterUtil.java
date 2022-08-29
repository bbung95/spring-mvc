package com.springbootweb.bootwebmvc.util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public class JsonFilterUtil {

    public static MappingJacksonValue filterJsonData(Object obj, String field, String filterName){

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept(field.split(","));

        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
        MappingJacksonValue mapping = new MappingJacksonValue(obj);
        mapping.setFilters(filters);

        return mapping;
    }
}
