package com.project.renew.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;


    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;


    public int getOrderItemId() {
        return orderItemId; }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId; }

    public Order getOrder() {
        return order; }
    public void setOrder(Order order) {
        this.order = order; }

    public Product getProduct() {
        return product; }
    public void setProduct(Product product) {
        this.product = product; }

    public double getPrice() {
        return price; }
    public void setPrice(double price) {
        this.price = price; }

    public int getQuantity() {
        return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity; }
}
