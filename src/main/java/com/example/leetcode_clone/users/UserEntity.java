package com.example.leetcode_clone.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.example.leetcode_clone.commons.Auditable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class UserEntity extends Auditable {
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false)
    private Boolean verified;
    @Column(nullable = false)
    private Boolean deactivated;

    @OneToOne(mappedBy = "user")
    private ProfileEntity profile;
}
