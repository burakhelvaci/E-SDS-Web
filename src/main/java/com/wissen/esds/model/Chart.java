package com.wissen.esds.model;

import java.io.Serializable;

public class Chart implements Serializable {

    private Personnel personnel;
    private long orderCount;

    public Chart(){}
    
    public Chart(Personnel personnel, long orderCount) {
        this.personnel = personnel;
        this.orderCount = orderCount;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(long orderCount) {
        this.orderCount = orderCount;
    }

}
