package com.nhnent.edu.springboot.autoconfig.conditional.conditionaldemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConditionalDemoConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void sayYesComponent() {
        assertThat(applicationContext.containsBean("sayYesComponent"), is(TRUE));
    }

    @Test
    public void sayYesComponentWeb() {
        assertThat(applicationContext.containsBean("sayYesComponentWeb"), is(FALSE));
    }

    @Test
    public void sayYesComponentNotWeb() {
        assertThat(applicationContext.containsBean("sayYesComponentNotWeb"), is(TRUE));
    }

    @Test
    public void sayYesComponentOnBean() {
        assertThat(applicationContext.containsBean("sayYesComponentOnBean"), is(TRUE));
    }

    @Test
    public void sayYesComponentOnMissingBean() {
        assertThat(applicationContext.containsBean("sayYesComponentOnMissingBean"), is(FALSE));
    }

    @Test
    public void sayYesComponentOnClass() {
        assertThat(applicationContext.containsBean("sayYesComponentOnClass"), is(TRUE));
    }

    @Test
    public void sayYesComponentOnMissingClass() {
        assertThat(applicationContext.containsBean("sayYesComponentOnMissingClass"), is(FALSE));
    }

    @Test
    public void sayYesComponentOnProperty() {
        assertThat(applicationContext.containsBean("sayYesComponentOnProperty"), is(TRUE));
    }

    @Test
    public void sayYesComponentOnResource() {
        assertThat(applicationContext.containsBean("sayYesComponentOnResource"), is(TRUE));
    }
}
