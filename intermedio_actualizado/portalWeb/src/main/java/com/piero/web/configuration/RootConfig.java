package com.piero.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.piero.web","com.piero.web.infraestructura","com.piero.web.security","com.piero.web.service"})
@PropertySource({"classpath:tiendaDS.properties"})
public class RootConfig {

}
