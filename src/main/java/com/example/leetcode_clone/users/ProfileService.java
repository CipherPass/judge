package com.example.leetcode_clone.users;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public ProfileEntity findEntityById(Long id) {
        ProfileEntity profile = this.profileRepository.findById(id).orElseThrow(() -> {
            throw new ProfileNotFoundException(id);
        });
        return profile;
    }

    public static class ProfileNotFoundException extends IllegalArgumentException {
        public ProfileNotFoundException(Long id) {
            super("Profile with id: "+ id + " not found");
        }
    }
}
