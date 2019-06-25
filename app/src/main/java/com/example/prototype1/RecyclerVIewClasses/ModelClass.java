package com.example.prototype1.RecyclerVIewClasses;

public class ModelClass {

    int ID;
    Float amount;
    String payment_method,category,description,date;
    boolean recurring;

    public ModelClass(int ID, Float amount, String payment_method, String category, String description, String date) {
        this.ID = ID;
        this.amount = amount;
        this.payment_method = payment_method;
        this.category = category;
        this.description = description;
        this.date = date;



    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




}
