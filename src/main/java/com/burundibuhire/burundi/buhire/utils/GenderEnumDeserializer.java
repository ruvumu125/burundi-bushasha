package com.burundibuhire.burundi.buhire.utils;

import com.burundibuhire.burundi.buhire.model.GenderEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class GenderEnumDeserializer extends JsonDeserializer<GenderEnum> {
    @Override
    public GenderEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.isEmpty()) {
            return null; // or return a default value
        }
        return GenderEnum.valueOf(value.toUpperCase());
    }
}
