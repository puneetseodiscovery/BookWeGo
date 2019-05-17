package com.bookwego.mainActivity.modelClasses;

public class CategoriesModel {

    String categoriesName="";
    String selectedValue="";

    public String getSelectedValue() {
        return selectedValue;
    }

    public boolean setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
        return false;
    }


    public CategoriesModel(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }
}
