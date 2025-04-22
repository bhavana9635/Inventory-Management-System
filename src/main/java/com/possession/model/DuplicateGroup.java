package com.possession.model;

import java.util.List;

public class DuplicateGroup {
    private String serialNumber;
    private List<Possession> items;
    private int count;

    public DuplicateGroup(String serialNumber, List<Possession> items) {
        this.serialNumber = serialNumber;
        this.items = items;
        this.count = items.size();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Possession> getItems() {
        return items;
    }

    public void setItems(List<Possession> items) {
        this.items = items;
        this.count = items.size();
    }

    public int getCount() {
        return count;
    }
} 