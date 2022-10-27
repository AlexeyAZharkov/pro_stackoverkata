package com.javamentor.qa.platform.webapp.configs;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan("com.javamentor.qa.platform")
@EntityScan("com.javamentor.qa.platform.models.entity")
public class JmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(JmApplication.class, args);
    }
}
