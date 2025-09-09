package com.project.renew.DTO;

import com.project.renew.model.Condition;
import com.project.renew.model.FurnitureType;

public class DonationRequest {
    private String donorName;
    private String donorPhone;
    private Condition furnitureCondition;
    private FurnitureType furnitureType;
    private String description;
    private String email;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
