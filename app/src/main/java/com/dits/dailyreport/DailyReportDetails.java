package com.dits.dailyreport;

public class DailyReportDetails {

    private String dinner,extraexpense,lunch,travelling;

    public DailyReportDetails() {
    }

    public DailyReportDetails(String dinner, String extraexpense, String lunch, String travelling) {
        this.dinner = dinner;
        this.extraexpense = extraexpense;
        this.lunch = lunch;
        this.travelling = travelling;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getExtraexpense() {
        return extraexpense;
    }

    public void setExtraexpense(String extraexpense) {
        this.extraexpense = extraexpense;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getTravelling() {
        return travelling;
    }

    public void setTravelling(String travelling) {
        this.travelling = travelling;
    }
}
