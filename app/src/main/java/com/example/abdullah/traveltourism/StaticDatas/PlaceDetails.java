package com.example.abdullah.traveltourism.StaticDatas;

/**
 * Created by abdullah on 6/23/17.
 */

public class PlaceDetails
{
    private static int Id;
    private static String Name;
    private static String Image;
    private static String Description;

    public PlaceDetails() {
    }

    public PlaceDetails(int id, String name, String image, String description) {
        Id = id;
        Name = name;
        Image = image;
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
