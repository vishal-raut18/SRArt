package com.srarts.security.user;

import com.srarts.user.entity.User;
import com.srarts.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier)
            throws UsernameNotFoundException {

        User user = userRepository
                .findByEmailIgnoreCase(identifier)
                .orElseGet(() ->
                        userRepository
                                .findByMobileNumber(identifier)
                                .orElseThrow(() ->
                                        new UsernameNotFoundException(
                                                "User not found"
                                        )
                                )
                );

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(user.getStatus().name().equals("BLOCKED"))
                .build();
    }
}