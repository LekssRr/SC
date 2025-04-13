package com.example.ServiceCompany.config;

import com.example.ServiceCompany.repository.AutoRepository;
import com.example.ServiceCompany.repository.ServiceCompanyRepository;
import com.example.ServiceCompany.service.AutoService;
import com.example.ServiceCompany.service.AutoServiceImpl;
import com.example.ServiceCompany.service.ServiceCompanyService;
import com.example.ServiceCompany.service.ServiceCompanyServiceImpl;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public AutoService autoService(AutoRepository autoRepository, ServiceCompanyRepository serviceCompanyRepository)
    {
        return new AutoServiceImpl(serviceCompanyRepository, autoRepository);
    }
    @Bean
    public ServiceCompanyService serviceCompanyService(AutoRepository autoRepository, ServiceCompanyRepository serviceCompanyRepository)
    {
        return new ServiceCompanyServiceImpl(serviceCompanyRepository, autoRepository);
    }
    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.example.ServiceCompany");
        factory.setDataSource(dataSource());
        return factory;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
