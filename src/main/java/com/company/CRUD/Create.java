package com.company.CRUD;

import com.company.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;

public class Create {

    public static void create_player(Map<Integer, Player> playerList) {
        System.out.println("Введите playerId игрока, которого хотите добавить");
        Scanner in = new Scanner(System.in);
        System.out.print("playerId: ");
        int playerId = in.nextInt();
        System.out.println("Nickname: ");
        String name = in.next();
        Player pl = new Player(playerId, name, null, null, null);
        Progress pr = createProgresses(pl);
        pl.getProgresses().add(pr);
        Currency cur = createCurrencies(pl);
        pl.getCurrencies().add(cur);
        Item item = createItems(pl);
        pl.getItems().add(item);
        try {
            Statement statement = Connect.statement();
            statement.executeUpdate(String.format("INSERT INTO player(PlayerId, Nickname) VALUES (%d, '%s')", pl.getPlayerId(), pl.getNickname()));
            for (Progress p : pl.progresses) {
                statement.executeUpdate(String.format("INSERT INTO progress(Id, PlayerId, ResourceId, Score, MaxScore) VALUES (%d, %d, %d, %d, %d)", p.getId(), p.getPlayerId(), p.getResourceId(), p.getScore(), p.getMaxScore()));
            }
            for (Currency c : pl.currencies) {
                statement.executeUpdate(String.format("INSERT INTO currency(Id, PlayerId, ResourceId, Name, Count) VALUES (%d, %d, %d, '%s', %d)", c.getId(), c.getPlayerId(), c.getResourceId(), c.getName(), c.getCount()));
            }
            for (Item i : pl.items) {
                statement.executeUpdate(String.format("INSERT INTO item(Id, PlayerId, ResourceId, Count, Level) VALUES (%d, %d, %d, %d, %d)", i.getId(), i.getPlayerId(), i.getResourceId(), i.getCount(), i.getLevel()));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Хотите завершить? Введите Y/N");
        String answer = in.nextLine();
        System.out.println(answer);
        if (answer.equals("N")) {
            Crud.change(playerList);
        }
    }


    private static Progress createProgresses(Player player) {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.print("Введите id: ");
        int id = in.nextInt();
        System.out.print("Введите resourceId: ");
        int resourceId = in.nextInt();
        System.out.print("Введите score: ");
        int score = in.nextInt();
        System.out.print("Введите maxScore: ");
        int maxScore = in.nextInt();
        return new Progress(id, player.getPlayerId(), resourceId, score, maxScore);

    }


    private static Currency createCurrencies(Player player) {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.print("Введите id: ");
        int id = in.nextInt();
        System.out.print("Введите resourceId: ");
        int resourceId = in.nextInt();
        System.out.print("Введите name: ");
        String name = in.next();
        System.out.print("Введите count: ");
        int count = in.nextInt();
        return new Currency(id, player.getPlayerId(), resourceId, name, count);
    }

    private static Item createItems(Player player) {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.print("Введите id: ");
        int id = in.nextInt();
        System.out.print("Введите resourceId: ");
        int resourceId = in.nextInt();
        System.out.print("Введите level: ");
        int level = in.nextInt();
        System.out.print("Введите count: ");
        int count = in.nextInt();
        return new Item(id, player.getPlayerId(), resourceId, count, level);
    }

}
