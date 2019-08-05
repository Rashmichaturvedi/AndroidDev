package com.dits.dailyreport;

class CustomerReportDetails {
    private String shopname;
    private String address;
    private String conversationdetails;
    private String feedback;
    private String personname;
    private String contactdetails;
    private  String Date;
    private  String Time;

    public CustomerReportDetails(){}

    public CustomerReportDetails(String shopname, String address, String conversationdetails, String feedback, String personname, String contactdetails,String Date,String Time) {
        this.shopname = shopname;
        this.address = address;
        this.conversationdetails = conversationdetails;
        this.feedback = feedback;
        this.personname = personname;
        this.contactdetails = contactdetails;
        this.Date = Date;
        this.Time= Time;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConversationdetails() {
        return conversationdetails;
    }

    public void setConversationdetails(String concersationdetails) {
        this.conversationdetails = concersationdetails;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getContactdetails() {
        return contactdetails;
    }

    public void setContactdetails(String contactdetails) {
        this.contactdetails = contactdetails;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
