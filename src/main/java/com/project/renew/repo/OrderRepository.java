package com.project.renew.repo;

import com.project.renew.model.Order;
import com.project.renew.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findTopByUserOrderByOrderIdDesc(User user);
    List<Order> findByUser(User user);

    List<Order> user(User user);
}
