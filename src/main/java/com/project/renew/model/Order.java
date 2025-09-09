package com.project.renew.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

    @Entity
    @Table(name = "Orders")
    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int orderId;


        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @Column(nullable = false)
        private String orderStatus;

        @Column(nullable = false)
        private double totalAmount;

        @Column(nullable = false)
        private LocalDate orderDate;


        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<OrderItem> items;


        public int getOrderId() {
            return orderId; }
        public void setOrderId(int orderId) {
            this.orderId = orderId; }

        public User getUser() { return user; }
        public void setUser(User user) {
            this.user = user; }

        public String getOrderStatus() {
            return orderStatus; }
        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus; }

        public double getTotalAmount() {
            return totalAmount; }
        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount; }

        public LocalDate getOrderDate() {
            return orderDate; }
        public void setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate; }

        public List<OrderItem> getItems() {
            return items; }
        public void setItems(List<OrderItem> items) {
            this.items = items; }
    }

