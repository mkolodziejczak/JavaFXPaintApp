package pl.edu.amu.bawsj.jpaint.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maciek on 2016-12-28.
 */
public class Command {

    private Drawable object;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String dateTime;

    Command(Drawable object)
    {
        this.object = object;

        Date date = new Date();
        dateTime = dateFormat.format(date);
    }

    public void setDone()
    {
        object.isRemoved = false;
    }

    public void setUndone()
    {
        object.isRemoved = true;
    }

    public String getName()
    {
        return object.getClass().getSimpleName();
    }

    public String getDateTime()
    {
        return dateTime;
    }
}
