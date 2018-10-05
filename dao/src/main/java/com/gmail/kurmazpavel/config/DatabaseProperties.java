package com.gmail.kurmazpavel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DatabaseProperties {

    @Autowired
    private Environment environment;

    private String databaseDriverName;
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;
    private String hibernateCurrentSessionContextClass;
    private String hibernateHbm2dll;
    private String hibernateCacheSecondLevel;
    private String hibernateCacheRegionFactoryClass;
    private String dataSourceClass;
    private Integer poolMaxSize;
    private String cachePreparedStatements;
    private String cachePreparedStatementsSize;
    private String cachePreparedStatementsSqlLimit;
    private String useServerPreparedStatements;

    @PostConstruct
    public void initialize() {
        this.databaseDriverName = environment.getProperty("database.driver.name");
        this.databaseUrl = environment.getProperty("database.url");
        this.databaseUsername = environment.getProperty("database.username");
        this.databasePassword = environment.getProperty("database.password");
        this.hibernateCurrentSessionContextClass = environment.getProperty("hibernate.current_session_context_class");
        this.hibernateHbm2dll = environment.getProperty("hibernate.hbm2ddl.auto");
        this.hibernateCacheSecondLevel = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.hibernateCacheRegionFactoryClass = environment.getProperty("hibernate.cache.region.factory_class");
        this.dataSourceClass = environment.getProperty("pool.data.source.class");
        this.poolMaxSize = Integer.parseInt(environment.getProperty("pool.max.size"));
        this.cachePreparedStatements = environment.getProperty("pool.cache.prepared.statements");
        this.cachePreparedStatementsSize = environment.getProperty("pool.cache.prepared.statements.size");
        this.cachePreparedStatementsSqlLimit = environment.getProperty("pool.cache.prepared.statements.sql.limit");
        this.useServerPreparedStatements = environment.getProperty("pool.use.server.prepared.statements");
    }


    public String getDatabaseDriverName() {
        return databaseDriverName;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getHibernateCurrentSessionContextClass() {
        return hibernateCurrentSessionContextClass;
    }

    public String getHibernateHbm2dll() {
        return hibernateHbm2dll;
    }

    public String getHibernateCacheSecondLevel() {
        return hibernateCacheSecondLevel;
    }

    public String getHibernateCacheRegionFactoryClass() {
        return hibernateCacheRegionFactoryClass;
    }

    public String getDataSourceclass() {
        return dataSourceClass;
    }

    public Integer getPoolMaxSize() {
        return poolMaxSize;
    }

    public String getCachePreparedStatements() {
        return cachePreparedStatements;
    }

    public String getCachePreparedStatementsSize() {
        return cachePreparedStatementsSize;
    }

    public String getCachePreparedStatementsSqlLimit() {
        return cachePreparedStatementsSqlLimit;
    }

    public String getUseServerPreparedStatements() {
        return useServerPreparedStatements;
    }
}
