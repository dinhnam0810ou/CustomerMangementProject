package com.ndn.model;

import java.util.List;

public class PaginatedResult<T> {
    private int count;
    private List<T> items;
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }
}
