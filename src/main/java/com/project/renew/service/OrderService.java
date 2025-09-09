package com.project.renew.service;

import com.project.renew.model.Order;
import com.project.renew.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository myOrderRepository;

    public List<Order> getAllOrders() {
        return myOrderRepository.findAll();
    }

    public Order saveOrder(Order myOrder) {
        return myOrderRepository.save(myOrder);
    }

    public void deleteOrder(int id) {
        myOrderRepository.deleteById(id);
    }
}
