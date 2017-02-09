package mafoe.jpaperformance.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Spring Configuration für die Persistenzschicht.
 *
 * @author EMAFOER
 * @version $Id$
 * @since 01.02.2017
 */
@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@EnableJpaRepositories(basePackages = "mafoe.jpaperformance.repository")
public class PersistenzConfig {

	/**
	 * Factory zum Erzeugen von EntityManagern.
	 */
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.H2);

		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setJpaVendorAdapter(vendorAdapter);
		// Pfad zu den Entitäten
		bean.setPackagesToScan("mafoe.jpaperformance");
		bean.setDataSource(dataSource());
		bean.setJpaProperties(hibernateProperties());
		return bean;
	}

	private Properties hibernateProperties() throws IOException {
		ClassPathResource resource = new ClassPathResource("hibernate.properties");
		return PropertiesLoaderUtils.loadProperties(resource);
	}

	@Bean
	JpaTransactionManager transactionManager() throws IOException {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}

	@Bean
	HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder
				.setType(EmbeddedDatabaseType.H2)
				.addScript("Initialschema.sql")
				.addScript("Initialdaten.sql")
				.build();
	}
}
