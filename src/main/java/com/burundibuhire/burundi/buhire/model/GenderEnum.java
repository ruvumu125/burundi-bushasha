package com.burundibuhire.burundi.buhire.model;

import com.burundibuhire.burundi.buhire.utils.GenderEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = GenderEnumDeserializer.class)
public enum GenderEnum {
    MALE,
    FEMALE;
}
