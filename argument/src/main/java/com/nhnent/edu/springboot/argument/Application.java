package com.nhnent.edu.springboot.argument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
class ArgumentViewer{
    private static final Logger logger = LoggerFactory.getLogger(ArgumentViewer.class);

    @Autowired
    private ApplicationArguments applicationArguments;

    @PostConstruct
    public void afterPropertySet() {
        boolean enable = applicationArguments.containsOption("enable");
        if(enable){
            logger.info("## > You added enable option.");
        }

        List<String> args = applicationArguments.getNonOptionArgs();
        logger.info("## > non option arguments ...");
        if ( !args.isEmpty() )
            args.forEach( fileArgument -> logger.info(fileArgument));
    }
}
