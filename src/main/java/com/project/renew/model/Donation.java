package com.project.renew.model;

import jakarta.persistence.*;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Integer donationId;

    @Column(name = "donor_name", nullable = false, length = 100)
    private String donorName;

    @Column(name = "donor_phone", nullable = false, length = 15)
    private String donorPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "furniture_condition", nullable = false)
    private Condition furnitureCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "furniture_type", nullable = false)
    private FurnitureType furnitureType;

    @Column(name = "furniture_description", columnDefinition = "TEXT" )
    private String furnitureDescription;

    @Column(name = "image", length = 255)
    private String imagePath;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Donation() {}



    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public Condition getFurnitureCondition() {
        return furnitureCondition;
    }

    public void setFurnitureCondition(Condition furnitureCondition) {
        this.furnitureCondition = furnitureCondition;
    }

    public FurnitureType getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(FurnitureType furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getFurnitureDescription() {
        return furnitureDescription;
    }

    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
