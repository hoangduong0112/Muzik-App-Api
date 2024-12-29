package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.User;
import com.hd.musik.entity.enums.Role;
import com.hd.musik.repository.UserRepository;
import com.hd.musik.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElse(null);
        if (user == null)
            throw new UsernameNotFoundException(String.format("User: %s does not exist", username));
        return user;
    }

    @Override
    public User saveUser(User user) {
        if(!user.getPassword().equals(user.getConfirmPassword()))
            return null;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER); //default user role
        user.setCreatedAt(new Date());
        this.userRepository.save(user);

        return user;
    }

}
