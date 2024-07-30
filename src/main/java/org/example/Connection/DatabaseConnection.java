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
public class DatabaseConnection {

    static HibernateConfig hibernateCfgController = new HibernateConfig();

    Properties properties;
    Session session;

    static DatabaseConnection connection = null;

    public DatabaseConnection(Properties properties, Session session) {
        this.properties = properties;
        this.session = session;
    }

    public DatabaseConnection() {
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
    public List<User> getAccountList(Authentication authentication) {
        Session session = getConnection().getSession();
        String request = "FROM User WHERE login = '" + authentication.getUsername() + "' AND password = '" + authentication.getPassword() + "'";
        return session.createQuery(request, User.class).list();
    }

    // adds item into database
    public void insertItem(Item item)
    {
        Session session = getConnection().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
    }

    // returns list of items by query
    public List<Item> getItemsList(String location,String name)
    {
        Session session = getConnection().getSession();
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
        return session.createQuery(request, Item.class).list();
    }

    //removes item from database
    public void removeItem(Item item)
    {
        Session session = getConnection().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(item);
        transaction.commit();
    }

    public static DatabaseConnection getConnection() {
        if(connection == null)
        {
            return new DatabaseConnection(hibernateCfgController.setProperties(),startDatabaseConnection());
        }
        return connection;
    }

    public static void setConnection(DatabaseConnection connection) {
        DatabaseConnection.connection = connection;
    }
}
