package org.example.Utils;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Getter
@Setter
public class SystemData {

    String user;
    String date;

    private static SystemData currentSystemData;

    public SystemData(String user, String date) {
        this.user = user;
        this.date = date;
    }

    public SystemData() {
    }

    public static String getCurrentSystemDate()
    {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        return date;
    }

    public SystemData getCurrentSystemData()
    {
        if(currentSystemData == null)
        {
            return  new SystemData("","");
        }
        return currentSystemData;
    }

    public void setCurrentSystemData(SystemData newData)
    {
        currentSystemData = newData;
    }
}
