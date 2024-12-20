package com.burundibuhire.burundi.buhire.utils;


import com.burundibuhire.burundi.buhire.model.DegreeEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DegreeEnumDeserializer extends JsonDeserializer<DegreeEnum> {
    @Override
    public DegreeEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.isEmpty()) {
            return null; // or return a default value
        }
        return DegreeEnum.valueOf(value.toUpperCase());
    }
}

