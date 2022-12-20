package com.company.CRUD;

import com.company.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;

public class Update {
    public static void update_player(Map<Integer, Player> playerList) {
        System.out.println("Введите playerId игрока, которого хотите изменить");
        Scanner in = new Scanner(System.in);
        System.out.print("playerId: ");
        int id_update = in.nextInt();
        System.out.println("Введите что именно Вы хотите изменить: -n - имя, -c - деньги, -p - прогресс, -i - предметы ");
        String argument = in.next();
        Player pl = PlayerCache.instanceOrThrow().playerList.get(id_update);
        switch (argument) {
            case "-n":
                System.out.println("Введите новое имя игрока: ");
                String new_nick = in.next();
                pl.setNickname(new_nick);
                playerList.put(id_update, pl);
                try {
                    Statement statement = Connect.statement();
                    statement.executeUpdate(String.format("UPDATE FROM player SET Nickname = '%s' WHERE PlayerId = %d", new_nick, id_update));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "-c":
                System.out.println("Введите количество валют игрока, которые хотите изменить: ");
                int number = in.nextInt();
                for (int i = 0; i < number; i++) {
                    System.out.println("Введите id валюты: ");
                    int currencyId = in.nextInt();
                    Currency currency = pl.getCurrencyById(currencyId);
                    Currency currNew = updateCurrency(currency);
                    pl.getCurrencies().remove(currency);
                    pl.getCurrencies().add(currNew);
                    try {
                        Statement statement = Connect.statement();
                        statement.executeUpdate(String.format("UPDATE FROM currency SET ResourceId = %d, Name = '%s', Count = %d WHERE Id = %d", currNew.getResourceId(), currNew.getName(), currNew.getCount(), currNew.getId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;
            case "-p":
                System.out.println("Введите количество прогрессов, которые хотите изменить: ");
                int n = in.nextInt();
                for (int i = 0; i < n; i++) {
                    System.out.println("Введите id прогресса: ");
                    int progressId = in.nextInt();
                    Progress progress = pl.getProgressById(progressId);
                    Progress progNew = updateProgress(progress);
                    pl.getProgresses().remove(progress);
                    pl.getProgresses().add(progNew);
                    try {
                        Statement statement = Connect.statement();
                        statement.executeUpdate(String.format("UPDATE FROM progress SET ResourceId = %d, Score = %d,  MaxScore = %d WHERE Id = %d", progNew.getResourceId(), progNew.getScore(), progNew.getMaxScore(), progNew.getId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;
            case "-i":
                System.out.println("Введите количество вещей, которые хотите изменить: ");
                int l = in.nextInt();
                for (int i = 0; i < l; i++) {
                    System.out.println("Введите id вещи: ");
                    int itemId = in.nextInt();
                    Item item = pl.getItemById(itemId);
                    Item itemNew = updateItem(item);
                    pl.getItems().remove(item);
                    pl.getItems().add(itemNew);
                    try {
                        Statement statement = Connect.statement();
                        statement.executeUpdate(String.format("UPDATE FROM item SET ResourceId = %d, Count = %d,  Level = %d WHERE Id = %d", itemNew.getResourceId(), itemNew.getCount(), itemNew.getLevel(), itemNew.getId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;
            default:
                System.out.println("Неверная команда");
                break;
        }
        System.out.println("Хотите завершить? Введите Y/N");
        String answer = in.nextLine();
        System.out.println(answer);
        if (answer.equals("N")) {
            Crud.change(playerList);

        }
    }


    public static Currency updateCurrency(Currency currency) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ресурс");
        int resourceId = in.nextInt();
        currency.setResourceId(resourceId);
        System.out.println("Введите имя");
        String name = in.next();
        currency.setName(name);
        System.out.println("Введите количество");
        int count = in.nextInt();
        currency.setCount(count);
        return currency;
    }

    public static Progress updateProgress(Progress progress) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ресурс");
        int resourceId = in.nextInt();
        progress.setResourceId(resourceId);
        System.out.println("Введите счет");
        int score = in.nextInt();
        if (score < progress.getMaxScore())
            progress.setScore(score);
        else {
            System.out.println("Счет не может быть больше максимального");
            System.out.println("Введите счет заново");
            score = in.nextInt();
            progress.setScore(score);
        }
        System.out.println("Введите максимальный счет");
        int maxScore = in.nextInt();
        progress.setMaxScore(maxScore);
        return progress;
    }

    public static Item updateItem(Item item) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ресурс");
        int resourceId = in.nextInt();
        item.setResourceId(resourceId);
        System.out.println("Введите уровень");
        int level = in.nextInt();
        item.setLevel(level);
        System.out.println("Введите количество");
        int count = in.nextInt();
        item.setCount(count);
        return item;
    }
}
