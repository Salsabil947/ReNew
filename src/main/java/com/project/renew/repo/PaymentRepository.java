package com.project.renew.repo;

import com.project.renew.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}