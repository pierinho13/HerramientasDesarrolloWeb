package com.piero.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.piero.infraestructura","com.piero.web.security"})
@PropertySource({"classpath:ds.properties"})
public class RootConfig {

}
