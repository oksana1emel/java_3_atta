package com.company.CRUD;

import com.company.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;

public class Crud {

    public static void change(Map<Integer, Player> playerList) {
        System.out.println("Введите команду, которую хотите выполнить: " +
                "-d - удаление игрока, -i - добавление игрока, -u - изменение данных об игроке ");
        Scanner in = new Scanner(System.in);
        String number_command = in.next();
        switch (number_command) {
            case "-d" -> Delete.delete_player();
            case "-с" -> Create.create_player(playerList);
            case "-u" -> Update.update_player(playerList);
            default -> System.out.println("Неверная команда");
        }
    }

}
