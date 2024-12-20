package com.burundibuhire.burundi.buhire.model;

import com.burundibuhire.burundi.buhire.utils.GenderEnumDeserializer;
import com.burundibuhire.burundi.buhire.utils.PoliticalCategoryEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PoliticalCategoryEnumDeserializer.class)
public enum PoliticalCategoryEnum {

    POLITICAL_PART,
    POLITICAL_COALITION,
    INDEPENDENT_CANDIDATE


}