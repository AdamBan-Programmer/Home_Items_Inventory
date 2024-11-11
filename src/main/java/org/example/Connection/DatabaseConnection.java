package org.example.Connection;

import lombok.Getter;
import lombok.Setter;
import org.example.Service.DatabaseConnectionService;
import org.hibernate.Session;

@Getter
@Setter
public final class DatabaseConnection {

    static DatabaseConnectionService databaseService = new DatabaseConnectionService();

    private static DatabaseConnection instance;
    private Session session;

    private DatabaseConnection(Session session) {
        this.session = session;
    }
    public static DatabaseConnection getInstance() {
        if(instance == null)
        {
            instance = new DatabaseConnection(databaseService.startSession());
        }
        return instance;
    }
}
