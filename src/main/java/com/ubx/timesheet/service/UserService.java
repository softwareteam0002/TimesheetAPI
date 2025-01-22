package com.ubx.timesheet.service;

import com.ubx.timesheet.model.User;
import com.ubx.timesheet.repository.UserRepository;
import com.ubx.timesheet.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserPrincipal.create(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
