package org.example.Utils;

import lombok.Getter;
import lombok.Setter;
import org.example.Authentication.AuthenticationStatusEnum;
import org.example.Entity.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Getter
@Setter
public final class SystemData {

    private static SystemData instance;
    private String username;
    private String date;
    private Enum status;

    public SystemData(String username, String date, Enum status) {
        this.username = username;
        this.date = date;
        this.status = status;
    }
    public static void reset()
    {
        instance = null;
    }
    public static SystemData getInstance()
    {
        if(instance == null)
        {
            instance = new SystemData("","", AuthenticationStatusEnum.LOGGED_OUT);
        }
        return instance;
    }
}
