package com.company.CRUD;

import com.company.Connect;
import com.company.Main;
import com.company.Player;
import com.company.PlayerCache;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
    public static void delete_player() {
        System.out.println("Введите playerId игрока, которого хотите удалить");
        Scanner in = new Scanner(System.in);
        System.out.print("playerId: ");
        int id_delete = in.nextInt();
        Player pl = PlayerCache.instanceOrThrow().playerList.remove(id_delete);
        try {
            Statement statement = Connect.statement();
            statement.executeUpdate(String.format("DELETE FROM player WHERE PlayerId = %d", id_delete));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Хотите завершить? Введите Y/N");
        String answer = in.nextLine();
        System.out.println(answer);
        if (answer.equals("N")) {
            Crud.change(PlayerCache.instanceOrThrow().playerList);
        }
    }
}
