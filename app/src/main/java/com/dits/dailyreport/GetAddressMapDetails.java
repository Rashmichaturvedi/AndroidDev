package com.dits.dailyreport;

class GetAddressMapDetails {
    private String location;
    public GetAddressMapDetails(){}
    public GetAddressMapDetails(String LL){
        this.location = LL ;    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String LL) {
        this.location = LL;
    }
}
