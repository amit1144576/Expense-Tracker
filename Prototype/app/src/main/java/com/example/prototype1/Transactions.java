package com.example.prototype1;
//package com.example.prototype1.firebaserealtimedatabase;


public class Transactions {

    String transId;
    Float amount;
    String category;
    String description;
    String date;
    String SwitchStatus;


    public Transactions(){

    }

    public Transactions(String transId, Float amount, String category, String description, String date, String switchStatus) {
        this.transId = transId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
        SwitchStatus = switchStatus;
    }

    public String getTransId() {
        return transId;
    }

    public Float getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getSwitchStatus() {
        return SwitchStatus;
    }
}
