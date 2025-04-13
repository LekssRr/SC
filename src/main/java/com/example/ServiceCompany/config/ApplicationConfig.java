package com.example.ServiceCompany.config;

import com.example.ServiceCompany.repository.AutoRepository;
import com.example.ServiceCompany.repository.ServiceCompanyRepository;
import com.example.ServiceCompany.service.AutoService;
import com.example.ServiceCompany.service.AutoServiceImpl;
import com.example.ServiceCompany.service.ServiceCompanyService;
import com.example.ServiceCompany.service.ServiceCompanyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
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
}
