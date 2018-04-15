package com.nhnent.edu.springboot.autoconfig.properties.propertiesdemo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EduPropertiesTest {

    @Autowired
    EduProperties eduProperties;

    @Test
    public void testEduProperties(){
        assertThat(eduProperties.getStudentMaxCount(), is(100L));
    }

}
