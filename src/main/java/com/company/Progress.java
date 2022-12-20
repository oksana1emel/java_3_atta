package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Progress {

    private int id;
    private int playerId;
    private int resourceId;
    private int score;
    private int maxScore;


    public Progress(@JsonProperty("id") int id,
                    @JsonProperty("playerId") int playerId,
                    @JsonProperty("resourceId") int resourceId,
                    @JsonProperty("score") int score,
                    @JsonProperty("maxScore") int maxScore) {
        this.id = id;
        this.playerId = playerId;
        this.resourceId = resourceId;
        this.score = score;
        this.maxScore = maxScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", resourceId=" + resourceId +
                ", score=" + score +
                ", maxScore=" + maxScore +
                '}';
    }
}
