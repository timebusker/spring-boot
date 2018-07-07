package com.timebusker.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
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
        entityManagerFactoryRef = "qjEntityManagerFactory",
        transactionManagerRef = "qjTransactionManager",
        basePackages = {"com.timebusker.repository.qj"}
)
public class QjDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "qjDataSource")
    @ConfigurationProperties(prefix = "qj.spring.datasource")
    public DataSource otherDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "qjEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean otherEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("qjDataSource") DataSource qjDataSource) {
        return builder
                .dataSource(qjDataSource)
                .packages("com.timebusker.entity.qj")
                .properties(jpaProperties.getHibernateProperties(qjDataSource))
                .persistenceUnit("qj")
                .build();
    }

    @Bean(name = "qjTransactionManager")
    public PlatformTransactionManager otherTransactionManager(
            @Qualifier("qjEntityManagerFactory") EntityManagerFactory qjEntityManagerFactory) {
        return new JpaTransactionManager(qjEntityManagerFactory);
    }
}
