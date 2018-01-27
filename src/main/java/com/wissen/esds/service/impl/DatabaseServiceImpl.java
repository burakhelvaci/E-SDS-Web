package com.wissen.esds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wissen.esds.dao.DatabaseDao;
import com.wissen.esds.model.Order;
import com.wissen.esds.model.OrderDetail;
import com.wissen.esds.model.Personnel;
import com.wissen.esds.service.DatabaseService;
import java.lang.reflect.Method;
import java.util.List;
import org.json.JSONArray;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    DatabaseDao databaseDao;

    @Override
    public <T> List<T> fetch(Class<T> classType) {
        return databaseDao.fetch(classType).getResultList();
    }

    @Override
    public String fetchOrderDetailData(Order order) {
        List<OrderDetail> list = databaseDao.fetch(OrderDetail.class).getResultList();
        JSONArray jSONArray = new JSONArray();
        for (OrderDetail orderDetail : list) {
            if (orderDetail.getOrder().getId() == order.getId()) {
                JSONArray jSONArrayElements = new JSONArray();
                jSONArrayElements.put(orderDetail.getProduct().getName());
                jSONArrayElements.put(orderDetail.getProduct().getPrice());
                jSONArrayElements.put(orderDetail.getProductCount());
                jSONArrayElements.put(orderDetail.getOrder().getOrderDate());
                jSONArray.put(jSONArrayElements);
            }
        }
        return jSONArray.toString();
    }

    @Override
    public String fetchGoogleChartData() {
        List<Personnel> list = databaseDao.fetch(Personnel.class).getResultList();

        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArrayTitle = new JSONArray();
        jSONArrayTitle.put("Task");
        jSONArrayTitle.put("Hours per Day");
        jSONArray.put(jSONArrayTitle);

        return jSONArray.toString();
    }

    @Override
    public <T> void insert(T object) {
        databaseDao.insert(object);
    }

    @Override
    public <T> void update(T object) {
        databaseDao.update(object);
    }

    @Override
    public <T> void delete(T object) {
        databaseDao.delete(object);
    }

    @Override
    public <T> boolean checkLogin(T object) {
        List<T> list = (List<T>) databaseDao.fetch(object.getClass()).getResultList();

        try {
            Method getUserName = object.getClass().getDeclaredMethod("getUserName");
            String userName = (String) getUserName.invoke(object);
            Method getPassword = object.getClass().getDeclaredMethod("getPassword");
            String password = (String) getPassword.invoke(object);

            for (T instance : list) {
                Method getUserNameInList = object.getClass().getDeclaredMethod("getUserName");
                String userNameInList = (String) getUserNameInList.invoke(object);
                Method getPasswordInList = object.getClass().getDeclaredMethod("getPassword");
                String passwordInList = (String) getPasswordInList.invoke(object);

                if (userNameInList.equals(userName) && passwordInList.equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }

        return false;
    }
}
