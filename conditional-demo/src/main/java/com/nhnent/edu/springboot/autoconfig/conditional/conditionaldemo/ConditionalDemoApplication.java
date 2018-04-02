package com.nhnent.edu.springboot.autoconfig.conditional.conditionaldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConditionalDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ConditionalDemoApplication.class, args);

		ConditionalDemoConfig.SayYesComponent sayYesComponent = context.getBean("sayYesComponent", ConditionalDemoConfig.SayYesComponent.class);
		sayYesComponent.sayYes();

		ConditionalDemoConfig.SayYesComponent sayYesComponentNotWeb = context.getBean("sayYesComponentNotWeb", ConditionalDemoConfig.SayYesComponent.class);
		sayYesComponentNotWeb.sayYes();

		ConditionalDemoConfig.SayYesComponent sayYesComponentOnBean = context.getBean("sayYesComponentOnBean", ConditionalDemoConfig.SayYesComponent.class);
		sayYesComponentOnBean.sayYes();

		ConditionalDemoConfig.SayYesComponent sayYesComponentOnClass = context.getBean("sayYesComponentOnClass", ConditionalDemoConfig.SayYesComponent.class);
		sayYesComponentOnClass.sayYes();

	}
}
