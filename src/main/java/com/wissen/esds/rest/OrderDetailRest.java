package com.wissen.esds.rest;

import com.wissen.esds.model.Order;
import com.wissen.esds.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.wissen.esds.service.DatabaseService;

@RestController
public class OrderDetailRest {

    @Autowired
    DatabaseService databaseService;

    @ResponseBody
    @RequestMapping(value = "orderDetail", method = RequestMethod.POST)
    public String getOrderDetail(Order order) {
        String query = "select products.`name`, products.price, "
                + " product_count, orders.order_date from orderdetail "
                + "inner join products on orderdetail.product_id=products.id "
                + "inner join categories on products.category_id=categories.id "
                + "inner join orders on orderdetail.order_id=orders.id "
                + "WHERE orderdetail.order_id=?";
        
        return databaseService.fetchOrderDetailData(query, OrderDetail.rowMapper(), order.getId());
    }
}
