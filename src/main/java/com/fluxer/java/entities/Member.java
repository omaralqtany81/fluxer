package com.fluxer.java.entities;

import com.fluxer.java.FluxerClient;
import java.util.List;

/**
 * Represents a user within a specific Guild. 
 * This is where you'll find permissions, roles, and nicknames.
 */
public class Member {
    private final User user;
    private final String guildId;
    private final String nickname;
    private final List<String> roleIds;
    private final long permissions;
    private final FluxerClient client;

    public Member(User user, String guildId, String nickname, List<String> roleIds, long permissions, FluxerClient client) {
        this.user = user;
        this.guildId = guildId;
        this.nickname = nickname;
        this.roleIds = roleIds;
        this.permissions = permissions;
        this.client = client;
    }

    public User getUser() { return user; }
    public String getGuildId() { return guildId; }
    public String getNickname() { return nickname != null ? nickname : user.getUsername(); }
    public List<String> getRoleIds() { return roleIds; }
    public long getPermissionsRaw() { return permissions; }

    /**
     * Checks if this member has a specific permission. 
     * Handled with bitfield logic under the hood.
     */
    public boolean hasPermission(Permission perm) {
        if (hasPermission(Permission.ADMINISTRATOR)) return true;
        return perm.hasPermission(this.permissions);
    }

    /**
     * Kicks this member from the guild. 
     * Use with caution.
     */
    public void kick() {
        client.getRestManager().post("/guilds/" + guildId + "/members/" + user.getId() + "/kick", "{}");
    }

    /**
     * Bans this member from the guild.
     */
    public void ban(int deleteMessageDays, String reason) {
        String json = String.format("{\"delete_message_days\": %d, \"reason\": \"%s\"}", deleteMessageDays, reason);
        client.getRestManager().post("/guilds/" + guildId + "/bans/" + user.getId(), json);
    }
}
