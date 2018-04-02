package com.nhnent.edu.springboot.autoconfig.conditional.conditionaldemo;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class ConditionalDemoConfig {
    @Bean
    @Conditional(PropertyCheckCondition.class)
    public SayYesComponent sayYesComponent() {
        return new SayYesComponent("Conditional");
    }

    @Bean
    @ConditionalOnWebApplication
    public SayYesComponent sayYesComponentWeb() {
        return new SayYesComponent("ConditionalOnWebApplication");
    }

    @Bean
    @ConditionalOnNotWebApplication
    public SayYesComponent sayYesComponentNotWeb() {
        return new SayYesComponent("ConditionalOnNotWebApplication");
    }


    @Bean
    @ConditionalOnBean(name="sayYesComponentNotWeb")
    public SayYesComponent sayYesComponentOnBean() {
        return new SayYesComponent("ConditionalOnBean");
    }

    @Bean
    @ConditionalOnMissingBean(name="sayYesComponentNotWeb")
    public SayYesComponent sayYesComponentOnMissingBean() {
        return new SayYesComponent("ConditionalOnMissingBean");
    }


    @Bean
    @ConditionalOnClass(value={ContextAnnotationAutowireCandidateResolver.class})
    public SayYesComponent sayYesComponentOnClass() {
        return new SayYesComponent("ConditionalOnClass");
    }

    @Bean
    @ConditionalOnMissingClass(value={"java.lang.String"})
    public SayYesComponent sayYesComponentOnMissingClass() {
        return new SayYesComponent("ConditionalOnMissingClass");
    }

    @Bean
    @ConditionalOnProperty(value="test")
    public SayYesComponent sayYesComponentOnProperty() {
        return new SayYesComponent("ConditionalOnProperty");
    }
    @Bean
    @ConditionalOnResource(resources = {"classpath:/test.txt"})
    public SayYesComponent sayYesComponentOnResource() {
        return new SayYesComponent("ConditionalOnResource");
    }

    class SayYesComponent{
        private String name;

        public SayYesComponent(String name) {
            this.name = name;
        }

        public void sayYes() {
            System.out.println("Y!E!S! " + this.name);
        }
    }

    static class PropertyCheckCondition implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String test = conditionContext.getEnvironment().getProperty("test");
            return test != null;
        }
    }
}

