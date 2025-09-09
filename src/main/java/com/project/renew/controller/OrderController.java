package com.project.renew.controller;

import com.project.renew.model.Order;
import com.project.renew.model.OrderItem;
import com.project.renew.model.Product;
import com.project.renew.model.User;
import com.project.renew.repo.OrderItemRepository;
import com.project.renew.repo.OrderRepository;
import com.project.renew.repo.ProductRepository;
import com.project.renew.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*" )
public class OrderController {

    @Autowired
    private UserRepository    userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderRepository   orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;


    @PostMapping("/")
    public Map<String, Integer> createOrder(@RequestBody Map<String, Object> payload) {
        System.out.println("Received Order Payload: " + payload);

        int userId = (int) payload.get("userId");
        double totalAmount = Double.parseDouble(payload.get("totalAmount").toString());

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: ID " + userId));

        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus("PENDING");
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepo.save(order);

        System.out.println("Order Created with ID: " + savedOrder.getOrderId());

        return Map.of("orderId", savedOrder.getOrderId());
    }



    @PostMapping("/{orderId}/items")
    public OrderItem addItemToOrder(@PathVariable int orderId, @RequestBody Map<String, Object> payload) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Object prodIdObj = payload.get("productId");
        Object qtyObj = payload.get("quantity");

        if (prodIdObj == null || qtyObj == null)
            throw new RuntimeException("Missing productId or quantity");

        int productId = ((Number) prodIdObj).intValue();
        int quantity = ((Number) qtyObj).intValue();


        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(product.getPrice());


        order.setTotalAmount(order.getTotalAmount() + product.getPrice() * quantity);
        orderRepo.save(order);

        return orderItemRepo.save(item);
    }



    @GetMapping("/{userId}")
    public List<OrderItem> viewMyOrder(@PathVariable int userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Order order = orderRepo.findTopByUserOrderByOrderIdDesc(user);
        if (order == null) {
            throw new RuntimeException("No order found for user");
        }


        return order.getItems();
    }


}
