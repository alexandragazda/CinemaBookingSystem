//package com.cinema.cinemaserver.repository;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//
//public class HibernateUtil {
//    private static SessionFactory sessionFactory = null;
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure("hibernate.cfg.xml");
//
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
//            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            return sessionFactory;
//        } catch (Throwable ex) {
//            System.err.println("SessionFactory creation failed." + ex);
//            ex.printStackTrace();
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null)
//            sessionFactory = buildSessionFactory();
//        return sessionFactory;
//    }
//
//    public static void closeSessionFactory(){
//        if(sessionFactory != null)
//            sessionFactory.close();
//    }
//}