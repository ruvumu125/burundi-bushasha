package com.burundibuhire.burundi.buhire.utils;

import com.burundibuhire.burundi.buhire.model.EthnicityEnum;
import com.burundibuhire.burundi.buhire.model.PoliticalCategoryEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PoliticalCategoryEnumDeserializer extends JsonDeserializer<PoliticalCategoryEnum> {
    @Override
    public PoliticalCategoryEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.isEmpty()) {
            return null; // or return a default value
        }
        return PoliticalCategoryEnum.valueOf(value.toUpperCase());
    }
}
