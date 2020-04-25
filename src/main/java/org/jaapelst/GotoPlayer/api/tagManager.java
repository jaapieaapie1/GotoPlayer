package org.jaapelst.GotoPlayer.api;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

public class tagManager {

    private HashMap<String, UUID> tags = new HashMap<String, UUID>();

    public tagManager() {

    }
    /**
     * Returns current tag HashMap.
     *
     * @return Tags hashmap.
     */
    public HashMap getTags() {
        return tags;
    }

    /**
     * Add a tag to the list
     * @param tagName Name of the tag
     * @param playerUUID UUID of the player you are tagging
     * @return true of false based on if it was successful of not
     */
    public boolean addTag(String tagName, UUID playerUUID) {
        if(tags.get(tagName) == null) {
            tags.put(tagName, playerUUID);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a tag
     * @param tagName the name of the tag that you are tyring to remove
     * @return true or false based on if it was successful or not
     */
    public boolean removeTag(String tagName) {
        if(tags.get(tagName) != null) {
            tags.remove(tagName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a tag by it's name
     * @param tagName the name of the tag you are trying to get
     * @return player UUID
     */
    public UUID getTag(String tagName) {
        UUID uuid = tags.get(tagName);
        return uuid;
    }

    /**
     * Get a tag by the UUID
     * @param uuid The uuid of the tag you are trying to get
     * @return tag name
     */
    public String getTag(UUID uuid) {
        String tagName = null;
        for (String i : tags.keySet() ) {
            if(tags.get(i) == uuid) tagName = i;
        }
        return tagName;
    }
}
