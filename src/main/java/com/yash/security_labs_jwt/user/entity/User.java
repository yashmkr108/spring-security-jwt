package com.yash.security_labs_jwt.user.entity;

import com.yash.security_labs_jwt.user.enums.UserRole;
import com.yash.security_labs_jwt.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    public User(String email,String password,UserRole role,UserStatus status){
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
