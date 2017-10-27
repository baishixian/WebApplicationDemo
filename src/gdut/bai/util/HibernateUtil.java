package gdut.bai.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate 工具类
 *
 * Session 是由SessionFactory负责创建的，而SessionFactory的实现是线程安全的，
 * 多个并发的线程可以同时访问一个 SessionFactory 并从中获取 Session 实例，而Session不是线程安全的。
 * Session 中包含了数据库操作相关的状态信息，那么说如果多个线程同时使用一个 Session 实例进行 CRUD，就很有可能导致数据存取的混乱
 */
public class HibernateUtil {

    /**
     *
     */
    private static SessionFactory sessionFactory;

    /**
     * 使用 ThreadLocal 保存当前业务线程中的 Hibernate Session
     *
     * ThreadLocal 并不是一个Thread，而是 thread local variable (线程局部变量)。
     * ThreadLocal 非常简单，就是为每一个使用该变量的线程都提供一个变量值的副本，每一个线程都可以独立地改变自己的副本，而不会和其它线程的副本冲突。
     * 从线程的角度看，就好像每一个线程都完全拥有一个该变量 (Map 类型 key-value 保存)。
     * ThreadLocal 这个类本身不是代表线程要访问的变量，这个类的成员变量才是。
     * 线程通过 ThreadLocal 的 get 和 set 方法去访问这个变量。
     *
     */
    private static ThreadLocal<Session> threadLocalSession = new ThreadLocal<>();

    /**
     *  Hibernate 配置
     */
    private static Configuration configuration = new Configuration();

    /**
     * 静态代码块
     */
    static {
        buildSessionFactory();
    }

    private HibernateUtil() {
    }

    /**
     * 获取 Session
     * @return
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {

        System.out.println("getSession");

        Session session = threadLocalSession.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                buildSessionFactory();
            }

            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            threadLocalSession.set(session);
        }

        return session;
    }


    /**
     * 关闭 Session
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = threadLocalSession.get();
        threadLocalSession.set(null);

        if (session != null) {
            session.close();
        }
    }

    /**
     * 获取 SessionFactory
     * @return SessionFactory
     *
     * @deprecated
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 获取 Configuration
     * @return Configuration
     *
     * @deprecated
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * 构建 SessionFactory
     */
    private static void buildSessionFactory() {
        System.out.println("start buildSessionFactory.");

        try {
            // 读取 Hibernate 的配置文件（默认 hibernate.cfg.xml）
            if (configuration == null){
                configuration = new Configuration();
            }
            configuration.configure("/hibernate.cfg.xml");

            // 创建 ServiceRegistry，通过 StandardServiceRegistryBuilder 构建并设置 Configuration 信息
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure() // 可以指定配置文件（默认hibernate.cfg.xml）
                    .build();

            try {
                // 创建 SessionFactory
                sessionFactory = new MetadataSources(serviceRegistry)
                        .buildMetadata()
                        .buildSessionFactory();
            }catch (Exception e){
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
                e.printStackTrace();
            }
        }catch (Exception e){
            System.err.println("Creating SessionFactory Error.");
            e.printStackTrace();
        }
    }

}
