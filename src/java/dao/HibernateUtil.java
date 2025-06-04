package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.net.URI;
import java.net.URISyntaxException;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            String databaseUrl = System.getenv("DATABASE_URL");
            if (databaseUrl != null) {
                URI dbUri = new URI(databaseUrl);

                String userInfo = dbUri.getUserInfo();
                if (userInfo == null) {
                    throw new RuntimeException("DATABASE_URL does not contain user info");
                }

                String[] userParts = userInfo.split(":");
                if (userParts.length < 2) {
                    throw new RuntimeException("DATABASE_URL user info format invalid");
                }

                String username = userParts[0];
                String password = userParts[1];
                String jdbcUrl = "jdbc:mysql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

                System.out.println("JDBC URL: " + jdbcUrl);
                System.out.println("Username: " + username);

                configuration.setProperty("hibernate.connection.url", jdbcUrl);
                configuration.setProperty("hibernate.connection.username", username);
                configuration.setProperty("hibernate.connection.password", password);
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            } else {
                System.out.println("DATABASE_URL environment variable not found!");
            }

            sessionFactory = configuration.buildSessionFactory();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Error parsing DATABASE_URL", e);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
