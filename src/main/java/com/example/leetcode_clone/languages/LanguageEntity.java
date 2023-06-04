package com.example.leetcode_clone.languages;

import com.example.leetcode_clone.commons.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "languages")
public class LanguageEntity extends Auditable {
    @Column(unique = true, nullable = false, length = 20)
    private String slug;

    @Column(nullable = false, length = 50)
    private String name;
}