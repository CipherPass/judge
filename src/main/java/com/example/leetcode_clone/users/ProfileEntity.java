package com.example.leetcode_clone.users;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.leetcode_clone.commons.Auditable;
import com.example.leetcode_clone.submittions.SubmittionEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "profiles")
public class ProfileEntity extends Auditable{
    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "problem")
    @Basic(fetch = FetchType.LAZY)
    private List<SubmittionEntity> allSubmittions;
}
