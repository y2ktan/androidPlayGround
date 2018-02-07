package com.example.vcfr67.androidtrainning.models;

/**
 * Created by VCFR67 on 2/4/2018.
 */

public class DataItem {
    private String itemId;
    private String itemName;
    private String description;

    public DataItem(Object o, String s, String s1, int i, double v, String s2) {
    }

    public DataItem(String itemId, String itemName, String description, String category, int sortPosition, double price, String image) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.category = category;
        this.sortPosition = sortPosition;
        this.price = price;
        this.image = image;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String category;
    private int sortPosition;
    private double price;
    private String image;

}
