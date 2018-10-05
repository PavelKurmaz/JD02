package com.gmail.kurmazpavel.config;

import com.gmail.kurmazpavel.*;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;
    @Autowired
    public DatabaseConfig(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setPoolName("AppConnectionPool");
        ds.setMaximumPoolSize(databaseProperties.getPoolMaxSize());
        ds.setDataSourceClassName(databaseProperties.getDataSourceclass());
        ds.addDataSourceProperty("url", databaseProperties.getDatabaseUrl());
        ds.addDataSourceProperty("user", databaseProperties.getDatabaseUsername());
        ds.addDataSourceProperty("password", databaseProperties.getDatabasePassword());
        ds.addDataSourceProperty("cachePrepStmts", databaseProperties.getCachePreparedStatements());
        ds.addDataSourceProperty("prepStmtCacheSize", databaseProperties.getCachePreparedStatementsSize());
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", databaseProperties.getCachePreparedStatementsSqlLimit());
        ds.addDataSourceProperty("useServerPrepStmts", databaseProperties.getUseServerPreparedStatements());
        return ds;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        //liquibase.setDropFirst(Boolean.TRUE);
        liquibase.setChangeLog("classpath:migration/db-changelog.xml");
        return liquibase;
    }

    @Bean
    @DependsOn("springLiquibase")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.put(Environment.HBM2DDL_AUTO, databaseProperties.getHibernateHbm2dll());
        properties.put(Environment.USE_SECOND_LEVEL_CACHE, databaseProperties.getHibernateCacheSecondLevel());
        properties.put(Environment.CACHE_REGION_FACTORY, databaseProperties.getHibernateCacheRegionFactoryClass());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(
                User.class,
                Audit.class,
                Address.class,
                Admin.class,
                Catalog.class,
                Order.class,
                News.class,
                Comment.class,
                Role.class,
                Permission.class,
                Bucket.class,
                Discount.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
