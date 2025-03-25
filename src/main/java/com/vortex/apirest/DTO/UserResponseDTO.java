package com.vortex.apirest.DTO;

import com.vortex.apirest.Entity.Role;
import com.vortex.apirest.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer available;
    private Role rol;

    public UserResponseDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.available = user.getAvailable();
        this.rol = user.getRol();
    }

}
