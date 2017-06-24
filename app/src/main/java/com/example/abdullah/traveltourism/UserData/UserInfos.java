package com.example.abdullah.traveltourism.UserData;

import android.net.Uri;

import java.net.URL;

/**
 * Created by abdullah on 6/7/17.
 */

public class UserInfos
{
    private static String name;
    private static String mail;
    private static URL imgurl;

    public UserInfos() {
    }

    public UserInfos(String name, String mail,URL imgurl) {
        this.name = name;
        this.mail = mail;
        this.imgurl=imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public static URL getImgurl() {
        return imgurl;
    }

    public static void setImgurl(URL imgurl) {
        UserInfos.imgurl = imgurl;
    }
}
