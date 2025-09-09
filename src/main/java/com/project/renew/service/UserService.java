package com.project.renew.service;

import com.project.renew.DTO.LogInDto;
import com.project.renew.DTO.SignUPDto;
import com.project.renew.model.Governorate;
import com.project.renew.model.User;
import com.project.renew.repo.GovernorateRepo;
import com.project.renew.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private GovernorateRepo govRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public String signup(SignUPDto dto) {
        Governorate gov = govRepo.findByName(dto.getGovernorate())
                .orElseThrow(() -> new RuntimeException("Governorate not found"));

        User user = new User();
        user.setGovernorate(gov);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());

        userRepo.save(user);
        return "ok";
    }

    public User login(LogInDto dto) {
        User user = userRepo.findByEmail(dto.getEmail()).orElse(null);

        if (user != null && encoder.matches(dto.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }
}
