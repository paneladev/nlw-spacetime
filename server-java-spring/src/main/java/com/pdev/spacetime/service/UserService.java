package com.pdev.spacetime.service;

import com.pdev.spacetime.entity.User;
import com.pdev.spacetime.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User findDetailedUser(Long id) {
        User user = userRepository.findDetailedById(id);
        return user;
    }

}
