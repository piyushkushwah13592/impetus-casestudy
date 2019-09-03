package com.mymovieportal.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO: Auto-generated Javadoc
/**
 * The Class DBConfiguration.
 */
@Configuration
@EnableTransactionManagement
public class DBConfiguration {

    /** The driver. */
    @Value("${spring.datasource.driver}")
    private String driver;

    /** The url. */
    @Value("${spring.datasource.url}")
    private String url;

    /** The username. */
    @Value("${spring.datasource.username}")
    private String username;

    /** The password. */
    @Value("${spring.datasource.password}")
    private String password;

    /** The dialect. */
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    /** The show sql. */
    @Value("${spring.jpa.hibernate.showsql}")
    private String showSql;

    /** The hbm 2 ddl auto. */
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddlAuto;

    /** The packages to scan. */
    @Value("${entitymanager.packagesToScan}")
    private String packagesToScan;

    /**
     * Data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Session factory.
     *
     * @return the local session factory bean
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(packagesToScan);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.show_sql", showSql);
        hibernateProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    /**
     * Transaction manager.
     *
     * @return the hibernate transaction manager
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
