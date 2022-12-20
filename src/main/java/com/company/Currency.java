package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {

    private int id;
    private int playerId;
    private int resourceId;
    private String name;
    private int count;

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }


    public Currency(@JsonProperty("id") int id,
                    @JsonProperty("playerId")  int playerId,
                    @JsonProperty("resourceId") int resourceId,
                    @JsonProperty("name") String name,
                    @JsonProperty("count") int count) {
        this.id = id;
        this.playerId = playerId;
        this.resourceId = resourceId;
        this.name = name;
        this.count = count;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
