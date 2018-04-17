package com.nhnent.edu.springboot.autoconfig.conditional.conditionaldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class ConditionalDemoConfig {

    /**
     * application.properties 파일에 test property 가 존재하면 아래 빈을 생성한다.
     * @return
     */
    //TODO (2) PropertyCheckCondition.class 를 조건으로 하는 @Conditional annotation 선언하기
    @Bean
    public SayYesComponent sayYesComponent() {
        return new SayYesComponent("Conditional");
    }

    /**
     * 웹어플리케이션이면 아래 빈을 생성한다.
     * @return
     */
    //TODO (3) 웹어플리케이션이면 동작하도록하는  @ConditionalOnXXXX annotation 선언하기
    @Bean
    public SayYesComponent sayYesComponentWeb() {
        return new SayYesComponent("ConditionalOnWebApplication");
    }

    /**
     * 웹어플리케이션이 아니면 아래 빈을 생성한다.
     * @return
     */
    //TODO (4) 웹어플리케이션이면 동작하도록하는  @ConditionalOnXXXX annotation 선언하기
    @Bean
    public SayYesComponent sayYesComponentNotWeb() {
        return new SayYesComponent("ConditionalOnNotWebApplication");
    }


    /**
     * sayYesComponentNotWeb 빈이 등록되어 있으면 아래 빈을 생성한다.
     * @return
     */
    //TODO (5) sayYesComponentNotWeb 빈이 등록되어 있으면 동작하도록하는  @ConditionalOnXXXX annotation 선언하기
    @Bean
    public SayYesComponent sayYesComponentOnBean() {
        return new SayYesComponent("ConditionalOnBean");
    }

    @Bean
    @ConditionalOnMissingBean(name="sayYesComponentNotWeb")
    public SayYesComponent sayYesComponentOnMissingBean() {
        return new SayYesComponent("ConditionalOnMissingBean");
    }


    @Bean
    @ConditionalOnClass(value={SpringApplication.class})
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
            // TODO (1)  application.properties 파일에 test property 가 존재하면 true 를 반환
            // Hint property 가져 오기 conditionContext.getEnvironment().getProperty("test")
            return false;
        }
    }
}

