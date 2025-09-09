package com.project.renew.service;

import com.project.renew.model.Donation;
import com.project.renew.model.FurnitureType;
import com.project.renew.model.Condition;
import com.project.renew.model.User;
import com.project.renew.repo.DonationRepository;
import com.project.renew.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    public Donation saveDonation(
            String email,
            String donorName,
            String donorPhone,
            Condition furnitureCondition,
            FurnitureType furnitureType,
            String furnitureDescription,
            MultipartFile imageFile
    ) throws IOException {

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("No user found for email: " + email);
        }
        User user = userOpt.get();


        Donation d = new Donation();
        d.setDonorName(donorName);
        d.setDonorPhone(donorPhone);
        d.setFurnitureCondition(furnitureCondition);
        d.setFurnitureType(furnitureType);
        d.setFurnitureDescription(furnitureDescription);
        d.setUserId(user.getId());


        if (imageFile != null && !imageFile.isEmpty()) {

            String uploadDir = System.getProperty("user.dir")
                    + File.separator + "uploads" + File.separator;
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            String fileName = UUID.randomUUID().toString()
                    + "_" + imageFile.getOriginalFilename();
            File dest = new File(uploadDir + fileName);
            imageFile.transferTo(dest);


            d.setImagePath("/uploads/" + fileName);
        }

        return donationRepository.save(d);
    }
}
