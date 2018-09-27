package com.gmail.kurmazpavel.util;

import com.gmail.kurmazpavel.*;
import com.gmail.kurmazpavel.config.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class HibernateUtil {
    private final Logger logger = LogManager.getLogger(HibernateUtil.class);
    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;
    private final DatabaseProperties databaseProperties;

    @Autowired
    public HibernateUtil(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, databaseProperties.getDatabaseDriverName());
                settings.put(Environment.URL, databaseProperties.getDatabaseUrl());
                settings.put(Environment.USER, databaseProperties.getDatabaseUsername());
                settings.put(Environment.PASS, databaseProperties.getDatabasePassword());
                settings.put(Environment.HBM2DDL_AUTO, databaseProperties.getHibernateHbm2dll());
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, databaseProperties.getHibernateCurrentSessionContextClass());
                settings.put(Environment.USE_SECOND_LEVEL_CACHE, databaseProperties.getHibernateCacheSecondLevel());
                settings.put(Environment.CACHE_REGION_FACTORY, databaseProperties.getHibernateCacheRegionFactoryClass());

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernamte Registry builder created");

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Audit.class)
                        .addAnnotatedClass(Address.class)
                        .addAnnotatedClass(Admin.class)
                        .addAnnotatedClass(Catalog.class)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(News.class)
                        .addAnnotatedClass(Comment.class)
                        .addAnnotatedClass(Role.class)
                        .addAnnotatedClass(Permission.class)
                        .addAnnotatedClass(Bucket.class)
                        .addAnnotatedClass(Discount.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("SessionFactory created");
            } catch (Exception e) {
                logger.error("Sessionfactory creation failed");
                logger.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}
