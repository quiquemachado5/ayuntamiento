package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.net.URI;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(); // carga hibernate.cfg.xml

            String databaseUrl = System.getenv("DATABASE_URL");
            if (databaseUrl != null) {
                URI dbUri = new URI(databaseUrl);

                String userInfo = dbUri.getUserInfo();
                String username = userInfo.split(":")[0];
                String password = userInfo.split(":")[1];
                String jdbcUrl = "jdbc:mysql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

                configuration.setProperty("hibernate.connection.url", jdbcUrl);
                configuration.setProperty("hibernate.connection.username", username);
                configuration.setProperty("hibernate.connection.password", password);
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            }

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
