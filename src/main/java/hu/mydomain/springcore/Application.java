package hu.mydomain.springcore;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import hu.mydomain.repositories.GameRepository;
import hu.mydomain.service.AllRepositoryImpl;

/** Egyszerű Spring környezet indítója
 * 
 * @author Klug Attila
 *
 */
@PropertySource(value = { "classpath:application.properties" })
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages={"hu.mydomain.intf,hu.mydomain.service,hu.mydomain.persistence"})
@EnableJpaRepositories(basePackages = "hu.mydomain.repositories")
@EntityScan(basePackages = "hu.mydomain.domain")
@EnableTransactionManagement
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        //ctx.getBean("pelda", AllRepositoryImpl.class);
    }
/*
    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        return sessionFactory;
    } */
    
}
