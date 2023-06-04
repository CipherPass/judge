package com.example.leetcode_clone.languages;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<LanguageEntity> getAllLangauges(){
        return this.languageRepository.findAll();
    }

    public LanguageEntity getDefaultLanguage() {
        return languageRepository.findById(Long.valueOf(1)).get();
    }
}
