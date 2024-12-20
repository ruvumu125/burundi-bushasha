package com.burundibuhire.burundi.buhire.model;

import com.burundibuhire.burundi.buhire.utils.DegreeEnumDeserializer;
import com.burundibuhire.burundi.buhire.utils.JobCategoryDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = JobCategoryDeserializer.class)
public enum JobCategoryEnum {

    POST_BY_DECREE,
    GENERAL_MANAGER,
    MANAGEMENT_FRAMEWORK,
    SUPPORT_FRAME,
    AGENT,
    SHORT_TERM_CONTRACT,
    DAILY_WORKER
}