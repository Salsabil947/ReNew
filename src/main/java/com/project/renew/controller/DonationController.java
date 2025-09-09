package com.project.renew.controller;

import com.project.renew.DTO.DonationRequest;
import com.project.renew.model.Donation;
import com.project.renew.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*")
public class DonationController {

    @Autowired
    private DonationService donationService;
    @PostMapping(value = "/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitDonation(
            @ModelAttribute DonationRequest donationRequest,
            @RequestParam(value = "imageUpload", required = false) MultipartFile imageFile
    ) {
        try {
            Donation saved = donationService.saveDonation(
                    donationRequest.getEmail(),
                    donationRequest.getDonorName(),
                    donationRequest.getDonorPhone(),
                    donationRequest.getFurnitureCondition(),
                    donationRequest.getFurnitureType(),
                    donationRequest.getDescription(),
                    imageFile
            );
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Error submitting donation: " + e.getMessage());
        }
    }


}




