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

		runOnNotNull(context, "sayYesComponent");

		runOnNotNull(context, "sayYesComponentWeb");
		runOnNotNull(context, "sayYesComponentNotWeb");

		runOnNotNull(context, "sayYesComponentOnBean");
		runOnNotNull(context, "sayYesComponentOnMissingBean");

		runOnNotNull(context, "sayYesComponentOnClass");
		runOnNotNull(context, "sayYesComponentOnMissingClass");

		runOnNotNull(context, "sayYesComponentOnProperty");
		runOnNotNull(context, "sayYesComponentOnResource");
	}


	private static void runOnNotNull(ConfigurableApplicationContext context, String beanName) {
		try {
			ConditionalDemoConfig.SayYesComponent sayYesComponent = context.getBean(beanName, ConditionalDemoConfig.SayYesComponent.class);
			if (sayYesComponent != null)
				sayYesComponent.sayYes();
		}catch (NoSuchBeanDefinitionException e) {
			log.info(beanName + " bean was not initiated!", e);
		}
	}
}
