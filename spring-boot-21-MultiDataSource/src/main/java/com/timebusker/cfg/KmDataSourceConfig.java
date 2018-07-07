package com.timebusker.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @DESC:DataSourceConfig
 * @author:timebusker
 * @date:2018/7/7
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "kmEntityManagerFactory",
        transactionManagerRef = "kmTransactionManager",
        basePackages = {"com.timebusker.repository.km"}
)
public class KmDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "kmDataSource")
    @ConfigurationProperties(prefix = "km.spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "kmEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("kmDataSource") DataSource kmDataSource) {
        return builder
                .dataSource(kmDataSource)
                .packages("com.timebusker.entity.km")
                .properties(jpaProperties.getHibernateProperties(kmDataSource))
                .persistenceUnit("test")
                .build();
    }

    @Primary
    @Bean(name = "kmTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("kmEntityManagerFactory") EntityManagerFactory kmEntityManagerFactory) {
        return new JpaTransactionManager(kmEntityManagerFactory);
    }

}
