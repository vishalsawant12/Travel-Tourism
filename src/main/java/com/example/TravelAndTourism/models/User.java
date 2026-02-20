package com.example.TravelAndTourism.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.Set;


@Entity
@Data
@Table(name = "users")

public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public Set<String> getRoles() {
                return roles;
        }

        public void setRoles(Set<String> roles) {
                this.roles = roles;
        }

        public boolean isActive() {
                return active;
        }

        public void setActive(boolean active) {
                this.active = active;
        }

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50)
        private String username;

        @NotBlank(message = "email cannot be blank")
        @Email(message = "Invalid email format")
        @Column(unique = true)
        private String email;

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8,message = "Password must be at least 8 characters long")
        private String password;

        @Column(name = "phone_number")
        private String phoneNumber;

        @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
        @Column(name = "role")
        private Set<String> roles; // e.g., ADMIN, USER, AGENT

        @Column(name = "is_active", nullable = false)
        private boolean active = true;



}


