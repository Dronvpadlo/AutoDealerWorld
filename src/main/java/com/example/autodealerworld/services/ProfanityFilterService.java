package com.example.autodealerworld.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfanityFilterService {
    private final Set<String> bannedWords = Set.of("shit", "bitch", "arsehole", "bollocks", "feck", "fuck", "pissed", "bastard", "jerk", "douchebag", "whore", "slut", "stupid", "dumd");

    public boolean containsBannedWords(String text) {
        String normalizedText = text.toLowerCase();
        return bannedWords.stream().anyMatch(normalizedText::contains);
    }
}
