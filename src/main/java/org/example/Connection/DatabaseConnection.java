package org.example.Connection;

import lombok.Getter;
import lombok.Setter;
import org.example.Authentication.Authentication;
import org.example.Entity.Item;
import org.example.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

@Getter
@Setter
public final class DatabaseConnection {

    static HibernateConfig hibernateCfgController = new HibernateConfig();

    private static DatabaseConnection instance;
    private Properties properties;
    private Session session;

    private DatabaseConnection(Properties properties, Session session) {
        this.properties = properties;
        this.session = session;
    }

    //estabilish connection with database
    public static Session startDatabaseConnection() {
        Properties properties = hibernateCfgController.setProperties();
        SessionFactory sessionFactory = new Configuration().addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

    //returns user
    public static List<User> getAccountList(Authentication authentication) {
        Session session = getInstance().getSession();
        String request = "FROM User WHERE login = '" + authentication.getUsername() + "' AND password = '" + authentication.getPassword() + "'";
        List<User> userList = session.createQuery(request, User.class).list();
        session.clear();
        return userList;
    }

    // adds item into database
    public static void insertItem(Item item)
    {
        Session session = getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.clear();
    }

    // returns list of items by query
    public static List<Item> getItemsList(String location,String name)
    {
        Session session = getInstance().getSession();
        String request = "FROM Item ";
        if(!location.equals("ALL"))
        {
            request += "WHERE Location = '" + location + "'";
            if(!name.isEmpty())
            {
                request+=" AND Name Like'%" + name+"%'";
            }
        }
        else {
            if(!name.isEmpty())
            {
                request+=" WHERE Name Like'%" + name+"%'";
            }
        }
        List<Item> itemList = session.createQuery(request, Item.class).list();
        session.clear();
        return itemList;
    }

    //removes item from database
    public static void removeItem(Item item)
    {
        Session session = getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(item);
        transaction.commit();
        session.clear();
    }

    public static DatabaseConnection getInstance() {
        if(instance == null)
        {
            instance = new DatabaseConnection(hibernateCfgController.setProperties(),startDatabaseConnection());
        }
        return instance;
    }
}
