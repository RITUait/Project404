package com.example.ritu.project404.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


//import static android.os.Build.ID;

/**
 * Created by ritu on 1/13/2018.
 */

public class Contact extends SugarRecord {
   // private String id;
    private String name;
    private String mobile;
    private String profile_imge;

    public Contact(){

    }

   // public String getId(){
    //    return id;
    //}

    //public void setId(String lid){
        //this.id = id;
   // }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }



    public String getProfile_imge(){
        return profile_imge;
    }

    public void setProfile_imge(){
        this.profile_imge = profile_imge;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                ", name='" + name + '\'' +
                ", profile_imge='" + profile_imge + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }




}
