package com.fluxer.java.leveling;

import com.fluxer.java.entities.Message;
import java.util.HashMap;
import java.util.Map;

public class LevelingManager {
    private final Map<String, UserStats> userStats = new HashMap<>();
    private final Map<Integer, String> roleRewards = new HashMap<>();

    public void addRoleReward(int level, String roleId) {
        roleRewards.put(level, roleId);
    }

    public void processMessage(Message message) {
        String userId = message.getAuthor().getId();
        UserStats stats = userStats.computeIfAbsent(userId, k -> new UserStats());
        stats.addXp(15 + (int)(Math.random() * 10));

        if (stats.canLevelUp()) {
            stats.levelUp();
            handleLevelUp(message, stats.getLevel());
        }
    }

    private void handleLevelUp(Message message, int newLevel) {
        message.reply("🎉 Congrats " + message.getAuthor().getAsMention() + "! You just reached Level " + newLevel + "!");
        
        String rewardRole = roleRewards.get(newLevel);
        if (rewardRole != null) {
            System.out.println("Awarding role " + rewardRole + " to " + message.getAuthor().getUsername());
        }
    }

    private static class UserStats {
        private int xp = 0;
        private int level = 0;

        public void addXp(int amount) { this.xp += amount; }
        public boolean canLevelUp() { return xp >= (level + 1) * 100; }
        public void levelUp() { this.level++; }
        public int getLevel() { return level; }
    }
}
