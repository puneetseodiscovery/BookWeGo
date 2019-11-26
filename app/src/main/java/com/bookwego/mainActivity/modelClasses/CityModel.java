package com.bookwego.mainActivity.modelClasses;

public class CityModel {
    String id="";
    String city_name="";

    public CityModel(String id, String city_name) {
        this.id = id;
        this.city_name = city_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
