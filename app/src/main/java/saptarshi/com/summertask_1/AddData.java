package saptarshi.com.summertask_1;

import io.realm.RealmObject;

/**
 * Created by Mahe on 5/25/2017.
 */

public class AddData extends RealmObject {
    public String title;
    public String details;

    public AddData(){}

    public AddData(String title,String details){
        this.title=title;
        this.details=details;
    }
}
