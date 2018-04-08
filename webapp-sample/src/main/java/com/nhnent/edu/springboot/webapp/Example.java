package com.nhnent.edu.springboot.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableAutoConfiguration
@SpringBootApplication
public class Example {

    public static void main(String[] args) throws Exception {
        //Example 1
        SpringApplication.run(Example.class, args);

        //Example 2
//        SpringApplication springApplication = new SpringApplication(Example.class);
//        springApplication.run(args);

        //Example 3
//        SpringApplication springApplication = new SpringApplication(Example.class);
//        springApplication.setBanner(new Banner() {
//            @Override
//            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//                out.print("Spring Boot!");
//            }
//        });
//        springApplication.run(args);

        //Example 4
//        new SpringApplicationBuilder()
//                .banner(new Banner() {
//                    @Override
//                    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//                        out.print("Spring Boot!");
//                    }
//                })
//                .sources(Example.class)
//                .run(args);

        //Example 5 - 계층 구조 (child context)
//        new SpringApplicationBuilder()
//                .banner(new Banner() {
//                    @Override
//                    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//                        out.print("Spring Boot!");
//                    }
//                })
//                .sources(Example.class)
//                .child(FirstChildConfig.class)
//                .run(args);

    }

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

}
