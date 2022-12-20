package com.company;

import com.company.CRUD.Crud;
import com.company.CRUD.Delete;
import com.company.CRUD.Update;
import com.company.Jetty.Server;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("players-test.json");
        var playerList = objectMapper.readValue(file, new TypeReference<List<Player>>() {
        })
                .stream()
                .collect(Collectors.toMap(Player::getPlayerId, it -> it));
        connect(playerList);
        PlayerCache.init(playerList);
        Server init  = new Server();
        init.initServer();
        //System.out.println(playerList.get(14681));
        //change(playerList);
    }

    public static void connect(Map<Integer, Player> playerList) {
        try {
            System.out.println("Connection to Store DB succesfull!");
            Statement statement =  Connect.statement();
            statement.execute("TRUNCATE currency, item, progress, player;");
            for (Player pl : playerList.values()) {
                statement.executeUpdate(String.format("INSERT INTO player(PlayerId, Nickname) VALUES (%d, '%s')", pl.getPlayerId(), pl.getNickname()));
                for (Currency c : pl.currencies) {
                    statement.executeUpdate(String.format("INSERT INTO currency(Id, PlayerId, ResourceId, Name, Count) VALUES (%d, %d, %d, '%s', %d)", c.getId(), c.getPlayerId(), c.getResourceId(), c.getName(), c.getCount()));
                }
                for (Item i : pl.items) {
                    statement.executeUpdate(String.format("INSERT INTO item(Id, PlayerId, ResourceId, Count, Level) VALUES (%d, %d, %d, %d, %d)", i.getId(), i.getPlayerId(), i.getResourceId(), i.getCount(), i.getLevel()));
                }
                for (Progress p : pl.progresses) {
                    statement.executeUpdate(String.format("INSERT INTO progress(Id, PlayerId, ResourceId, Score, MaxScore) VALUES (%d, %d, %d, %d, %d)", p.getId(), p.getPlayerId(), p.getResourceId(), p.getScore(), p.getMaxScore()));
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
