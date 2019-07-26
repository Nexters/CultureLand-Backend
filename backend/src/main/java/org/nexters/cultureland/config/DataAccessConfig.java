package org.nexters.cultureland.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
public class DataAccessConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory
			= new LocalContainerEntityManagerFactoryBean();
		
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("org.nexters.cultureland.model.vo");
		factory.setJpaVendorAdapter(jpaVendorAdapters());
		factory.setJpaProperties(jpaProperties());

		return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
	}
	
	private JpaVendorAdapter jpaVendorAdapters() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		
		return hibernateJpaVendorAdapter;
	}
	
	private Properties jpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.show_sql", "true");
		jpaProperties.setProperty("hibernate.format_sql", "true");
		jpaProperties.setProperty("hibernate.use_sql_comments", "true");
		jpaProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
		jpaProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
		
		return jpaProperties;
	}

}
