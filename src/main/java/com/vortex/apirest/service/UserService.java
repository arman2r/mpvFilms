package com.vortex.apirest.service;

import com.vortex.apirest.ConfigProject.JwtUtil;
import com.vortex.apirest.DTO.UserDTO;
import com.vortex.apirest.DTO.UserResponseDTO;
import com.vortex.apirest.Entity.User;
import com.vortex.apirest.Repository.UserRepository;
import com.vortex.apirest.Entity.Role;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder; 


    public UserService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil; 
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAvailable(userDTO.getAvailable());
        user.setRol(userDTO.getRol());
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public List<User> listUsersWithRoleUser() {
        return userRepository.findByRol(Role.USER);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setAvailable(userDTO.getAvailable());
            user.setRol(userDTO.getRol());
            if (userDTO.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            return userRepository.save(user);
        }
        return null; // O lanzar una excepción si el usuario no existe
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
    
        return jwtUtil.generateToken(user.getEmail());
    }

    public UserDTO getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return null; // Si el usuario no existe, devolvemos null
        }

        User user = userOptional.get();

        // Crear manualmente el UserDTO con los valores de User
        UserDTO userDTO = new UserDTO();
        userDTO.setAvailable(user.getAvailable());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setRol(user.getRol());

        return userDTO;
    }
}