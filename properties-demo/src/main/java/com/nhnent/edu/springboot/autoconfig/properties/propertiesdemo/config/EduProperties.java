package com.nhnent.edu.springboot.autoconfig.properties.propertiesdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix="nhn.edu")
@Validated
public class EduProperties {
    @NotNull
    @Min(10)
    private Long studentMaxCount=10L;

    public Long getStudentMaxCount() {
        return studentMaxCount;
    }

    public void setStudentMaxCount(Long studentMaxCount) {
        this.studentMaxCount = studentMaxCount;
    }
}
