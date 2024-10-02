package com.synergisticit.config;


import com.synergisticit.util.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {

    //Use Environment env
    //Use @Value

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }

    @Bean
    InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver =  new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
        viewResolver.setPrefix("WEB-INF/jsp/");
        return viewResolver;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){


        return new BCryptPasswordEncoder();
    }

}
