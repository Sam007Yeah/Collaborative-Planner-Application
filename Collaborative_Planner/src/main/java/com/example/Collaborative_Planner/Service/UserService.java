package com.example.Collaborative_Planner.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Collaborative_Planner.Entities.UserEntity;
import com.example.Collaborative_Planner.Repository.UserRepository;
import com.example.Collaborative_Planner.Utils.RegisterDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity updateUser(Long id, UserEntity updates) {
        // 1. Fetch existing user from database
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // 2. Update only non-null fields
        if (updates.getUsername() != null) {
            existingUser.setUsername(updates.getUsername());
        }
        if (updates.getEmail() != null) {
            existingUser.setEmail(updates.getEmail());
        }
        if (updates.getFirstName() != null) {
            existingUser.setFirstName(updates.getFirstName());
        }
        if (updates.getLastName() != null) {
            existingUser.setLastName(updates.getLastName());
        }
        if (updates.getPassword() != null) {
            existingUser.setPassword(updates.getPassword());
        }

        // 3. Save and return (updatedAt will auto-update)
        return userRepository.save(existingUser);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public void registerUser(RegisterDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername()) || userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Username already exists");
        }
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    public UserEntity loadUserForLogin(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return user;
    }

}
