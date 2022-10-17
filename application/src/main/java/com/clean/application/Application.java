package com.clean.application;

import com.clean.common.mediator.spring.SpringMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.clean.persistence")
public class Application {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public com.clean.common.mediator.core.IMediator getMediator(){
        return  new SpringMediator(applicationContext,"com.clean.application");
    }
}
