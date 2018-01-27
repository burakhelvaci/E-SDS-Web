package com.wissen.esds.service;

import com.wissen.esds.model.Order;
import java.util.List;

public interface DatabaseService {

    <T> List<T> fetch(Class<T> classType);

    String fetchOrderDetailData(Order order);

    String fetchGoogleChartData();
    
    <T> void insert(T object);
    
    <T> void update(T object);
    
    <T> void delete(T object);

    <T> boolean checkLogin(T object);
}
