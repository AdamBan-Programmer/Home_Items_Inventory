package org.example.Utils;

import lombok.Getter;
import lombok.Setter;
import org.example.Entity.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Getter
@Setter
public final class SystemData {

    String user;
    String date;

    private static SystemData currentSystemData;

    private SystemData(String user, String date) {
        this.user = user;
        this.date = date;
    }
    public static String getCurrentSystemDate()
    {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        return date;
    }

    public static void updateSystemData(User currentUser)
    {
        SystemData systemData = SystemData.getInstance();
        systemData.setUser(currentUser.getName());
        systemData.setDate(getCurrentSystemDate());
    }
    public static SystemData getInstance()
    {
        if(currentSystemData == null)
        {
            currentSystemData = new SystemData("","");
        }
        return currentSystemData;
    }
}
