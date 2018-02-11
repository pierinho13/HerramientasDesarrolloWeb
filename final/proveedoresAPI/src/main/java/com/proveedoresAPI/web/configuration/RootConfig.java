package com.proveedoresAPI.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.proveedoresAPI.web","com.proveedoresAPI.web.infraestructura","com.proveedoresAPI.web.security"})
@PropertySource({"classpath:ds.properties"})
public class RootConfig {

}
