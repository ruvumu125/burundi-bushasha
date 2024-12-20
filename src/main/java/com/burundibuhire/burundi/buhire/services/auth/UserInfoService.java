package com.burundibuhire.burundi.buhire.services.auth;

import com.burundibuhire.burundi.buhire.dto.auth.UserDetailsImpl;
import com.burundibuhire.burundi.buhire.model.User;
import com.burundibuhire.burundi.buhire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usernot found"));

        return new UserDetailsImpl(user);
    }

}