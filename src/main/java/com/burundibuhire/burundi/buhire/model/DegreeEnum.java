package com.burundibuhire.burundi.buhire.model;

import com.burundibuhire.burundi.buhire.utils.DegreeEnumDeserializer;
import com.burundibuhire.burundi.buhire.utils.GenderEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = DegreeEnumDeserializer.class)
public enum DegreeEnum {
    PRIMAIRE,
    A3,
    SECONDAIRE,
    A1,
    BACHELOR,
    MASTER,
    DOCTORATE
}