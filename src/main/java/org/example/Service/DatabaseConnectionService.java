package org.example.Service;

import org.example.Connection.DatabaseConnection;
import org.example.Connection.HibernateConfig;
import org.example.Authentication.Authentication;
import org.example.Entity.Item;
import org.example.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

public class DatabaseConnectionService {

    static HibernateConfig hibernateCfgController = new HibernateConfig();

    //estabilish connection with database
    public Session startSession() {
        Properties properties = hibernateCfgController.setProperties();
        SessionFactory sessionFactory = new Configuration().addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

    //returns user
    public List<User> getAccounts(Authentication authentication) {
        Session session = DatabaseConnection.getInstance().getSession();
        String request = "FROM User WHERE login = '" + authentication.getUsername() + "' AND password = '" + authentication.getPassword() + "'";
        List<User> userList = session.createQuery(request, User.class).list();
        session.clear();
        return userList;
    }

    // adds item into database
    public void insertItem(Item item)
    {
        Session session = DatabaseConnection.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.clear();
    }

    // returns list of items by query
    public List<Item> getItemsList(String location,String name)
    {
        Session session = DatabaseConnection.getInstance().getSession();
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
    public void removeItem(Item item)
    {
        Session session = DatabaseConnection.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(item);
        transaction.commit();
        session.clear();
    }
}
