package org.example.Service;

import org.example.Entity.User;
import org.example.Utils.SystemData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SystemDataService {

    public void updateSystemData(User currentUser)
    {
        SystemData systemData = SystemData.getInstance();
        systemData.setUsername(currentUser.getName());
        systemData.setDate(getCurrentSystemDate());
    }

    private String getCurrentSystemDate()
    {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        return date;
    }
}
