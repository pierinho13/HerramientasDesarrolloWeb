package com.proveedoresAPI.web.configuration;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = { "com.proveedoresAPI.web.infraestructura.repository" })
@EnableTransactionManagement
public class PersistenceContext {

	@Autowired
	Environment env;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}

	private HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		Properties dataSourceProperties = new Properties();
		dataSourceProperties.setProperty("url", env.getProperty("db.proveedoresAPI.url"));
		dataSourceProperties.setProperty("user", env.getProperty("db.proveedoresAPI.user"));
		dataSourceProperties.setProperty("password", env.getProperty("db.proveedoresAPI.password"));
		hikariConfig.setDataSourceProperties(dataSourceProperties);
		hikariConfig.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
		hikariConfig.setPoolName("springHirakiPool");
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setMaximumPoolSize(500);
		hikariConfig.setMinimumIdle(20);
		hikariConfig.setMaxLifetime(360000);
		return hikariConfig;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.proveedoresAPI");

		Properties jpaProperties = new Properties();

		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		
			
		logger.debug("ATENCION: Perfil DEV. Se ha activado hibernate.hbm2ddl.auto y se recrearan las tablas según su configuración: " +env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}