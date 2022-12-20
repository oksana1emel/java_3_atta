package com.company;

import java.util.Map;

public class PlayerCache {

    private static PlayerCache instance;

    public static void init(Map<Integer, Player> playerList) {
        if (instance == null) {
            instance = new PlayerCache(playerList);
        }
    }

    public static PlayerCache instanceOrThrow() {
        if (instance != null) {
            return instance;
        }
        throw new IllegalStateException("Player cache is not initialize");
    }

    public final Map<Integer, Player> playerList;

    private PlayerCache(Map<Integer, Player> playerList) {
        this.playerList = playerList;
    }
}
