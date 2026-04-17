package com.fluxer.java.automod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AutoModManager {
    private final List<Pattern> blacklistedPatterns = new ArrayList<>();
    private boolean aiEnabled = false;

    public void addBlacklist(String regex) {
        blacklistedPatterns.add(Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
    }

    public boolean shouldFilter(String content) {
        for (Pattern p : blacklistedPatterns) {
            if (p.matcher(content).find()) return true;
        }
        return false;
    }

    public void enableAI(boolean enabled) {
        this.aiEnabled = enabled;
    }
}
