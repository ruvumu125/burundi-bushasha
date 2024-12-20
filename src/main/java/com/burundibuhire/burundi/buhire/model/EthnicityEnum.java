package com.burundibuhire.burundi.buhire.model;

import com.burundibuhire.burundi.buhire.utils.DegreeEnumDeserializer;
import com.burundibuhire.burundi.buhire.utils.EthnicityEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EthnicityEnumDeserializer.class)
public enum EthnicityEnum {
    GANWA,
    HUTU,
    TUTSI,
    TWA
}