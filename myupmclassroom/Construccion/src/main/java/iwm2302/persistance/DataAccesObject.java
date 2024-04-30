package iwm2302.persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DataAccesObject {
    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;
    private Session session;

    public DataAccesObject() {
        registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        session = sessionFactory.openSession();
    }

    public void newSession() {
        session = sessionFactory.openSession();
    }

    public Session getSession() {
        return session;
    }

    public void destroy() {
        session.close();
        sessionFactory.close();
        registry.close();
    }

}
