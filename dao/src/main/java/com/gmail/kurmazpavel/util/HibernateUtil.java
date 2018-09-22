package com.gmail.kurmazpavel.util;

import com.gmail.kurmazpavel.*;
import com.gmail.kurmazpavel.config.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;


public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
                settings.put(Environment.URL, ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_URL));
                settings.put(Environment.USER, ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_USERNAME));
                settings.put(Environment.PASS, ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_PWD));
                settings.put(Environment.HBM2DDL_AUTO, ConfigurationManager.getInstance().getProperty(Environment.HBM2DDL_AUTO));
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, ConfigurationManager.getInstance().getProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS));
                settings.put(Environment.USE_SECOND_LEVEL_CACHE, ConfigurationManager.getInstance().getProperty(Environment.USE_SECOND_LEVEL_CACHE));
                settings.put(Environment.CACHE_REGION_FACTORY, ConfigurationManager.getInstance().getProperty(Environment.CACHE_REGION_FACTORY));

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
