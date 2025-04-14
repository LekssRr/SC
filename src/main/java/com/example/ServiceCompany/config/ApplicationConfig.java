package com.example.ServiceCompany.config;

import com.example.ServiceCompany.repository.AutoRepository;
import com.example.ServiceCompany.repository.ServiceCompanyRepository;
import com.example.ServiceCompany.service.AutoService;
import com.example.ServiceCompany.service.AutoServiceImpl;
import com.example.ServiceCompany.service.ServiceCompanyService;
import com.example.ServiceCompany.service.ServiceCompanyServiceImpl;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example.ServiceCompany")
public class ApplicationConfig {

    @Bean
    public AutoService autoServiceBean(AutoRepository autoRepository, ServiceCompanyRepository serviceCompanyRepository) {
        return new AutoServiceImpl(serviceCompanyRepository, autoRepository);
    }
    @Bean
    public ServiceCompanyService serviceCompanyServiceBean(AutoRepository autoRepository, ServiceCompanyRepository serviceCompanyRepository) {
        return new ServiceCompanyServiceImpl(serviceCompanyRepository, autoRepository);
    }
}
