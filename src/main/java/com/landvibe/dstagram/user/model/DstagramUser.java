package com.landvibe.dstagram.user.model;


import com.landvibe.dstagram.security.model.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DstagramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private boolean enable;

    public AuthUser toAuthUser() {
        return new AuthUser(email, password);
    }
}
