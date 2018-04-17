package com.nhnent.edu.springboot.autoconfig.conditional.conditionaldemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConditionalDemoApplication {

	private static final Log log = LogFactory.getLog(ConditionalDemoApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ConditionalDemoApplication.class, args);
	}

}
