package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    private int id;
    private int playerId;
    private int resourceId;
    private int count;
    private int level;

    public Item(@JsonProperty("id") int id,
                @JsonProperty("playerId") int playerId,
                @JsonProperty("resourceId") int resourceId,
                @JsonProperty("count") int count,
                @JsonProperty("level") int level) {
        this.id = id;
        this.playerId = playerId;
        this.resourceId = resourceId;
        this.count = count;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", resourceId=" + resourceId +
                ", count=" + count +
                ", level=" + level +
                '}';
    }
}
