package com.github.erodriguezg.springbootangular.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import com.github.erodriguezg.springbootangular.utils.ConstantesUtil;

@Configuration
public class PersistenceConfig {

	private static final String QUALIFIER_JPA_PROPERTIES = "jpaProperties";

	@Bean
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf.getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("hikari") DataSource dataSource,
			@Qualifier(QUALIFIER_JPA_PROPERTIES) Properties jpaProperties) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPersistenceUnitName("persistenceUnit");
		emf.setPackagesToScan("com.github.erodriguezg.springbootangular.services.entities");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(jpaProperties);
		return emf;
	}

	@Bean
	@Qualifier(QUALIFIER_JPA_PROPERTIES)
	@Profile(ConstantesUtil.SPRING_BOOT_PROFILE_NAME_DEVELOPMENT)
	public Properties jpaPropertiesMapDevelopment() {
		Properties jpaProperties = crearJpaPropertiesComun();
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.format_sql", "true");
		return jpaProperties;
	}

	@Bean
	@Qualifier(QUALIFIER_JPA_PROPERTIES)
	@Profile(ConstantesUtil.SPRING_BOOT_PROFILE_NAME_PRODUCTION)
	public Properties jpaPropertiesMapProduction() {
		Properties jpaProperties = crearJpaPropertiesComun();
		jpaProperties.put("hibernate.show_sql", "false");
		jpaProperties.put("hibernate.format_sql", "false");
		return jpaProperties;
	}

	private Properties crearJpaPropertiesComun() {
		Properties props = new Properties();
		// props.put("hibernate.max_fetch_depth", "0"); comentado para ocupar
		// el defecto
		props.put("hibernate.connection.charSet", "UTF-8");
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
		props.put("hibernate.jdbc.lob.non_contextual_creation", "true");
		return props;
	}

	@Bean
	@Qualifier("hikari")
	public DataSource dataSource(@Value("${app.db.jdbcurl}") String jdbcUrl, @Value("${app.db.user}") String user,
			@Value("${app.db.password}") String password, @Value("${app.db.poolsize}") int poolSize) {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setJdbcUrl(jdbcUrl);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setMaximumPoolSize(poolSize);
		return ds;
	}

}
