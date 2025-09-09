package com.project.renew.controller;

import com.project.renew.model.Order;
import com.project.renew.model.Payment;
import com.project.renew.model.User;
import com.project.renew.repo.OrderRepository;
import com.project.renew.repo.PaymentRepository;
import com.project.renew.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/Payment")
    public String Payment(
            @RequestParam int Id,
            @RequestParam String cardNumber,
            @RequestParam String expirationDate,
            @RequestParam String cvv
    ) {
        Optional<User> user = userRepository.findById(Id);
        if (user.isEmpty()) {
            return "User not found!";
        }

        Order order = orderRepository.findTopByUserOrderByOrderIdDesc(user.get());
        if (order == null) {
            return "Order not found!";
        }

        Payment payment = new Payment();
        payment.setUserId(user.get().getId());
        payment.setOrderId(order.getOrderId());
        payment.setCardNumber(cardNumber);
        payment.setExpirationDate(LocalDate.parse(expirationDate));
        payment.setCvv(cvv);
        payment.setPaymentStatus("Paid");
        paymentRepository.save(payment);

        return "Payment successful!";
    }
}
