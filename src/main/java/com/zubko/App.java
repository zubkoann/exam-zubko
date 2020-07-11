package com.zubko;

import com.zubko.connectors.UserDatabaseConnector;
import com.zubko.models.User;

import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static String action;

    public static void main(String[] args) {
        User newU = new User("Anna", "Zubko", "123@i.ua", "dir", "fintech");
        System.out.println("Input Action:" +
                "просмотр списка уже существующих пользователей - listOfUsers" +
                "добавление нового пользователя - insertUser" +
                "удаление пользователя - delete" +
                "поиск пользователя по id / email / фамилии  " +
                "показать список оборудования у пользователя" +
                "список всего оборудования" +
                "добавление нового оборудования \"на склад\" " +
                "удаление оборудования (нельзя удалить, если оборудование выдано пользователю)" +
                "выдача оборудования пользователю" +
                "поиск оборудования по типу / по \"выдано/насклда\" / по пользователю" +
                "создание отчета по пользователям - текстовый файл" +
                "выход из программы - exit" );
        action = sc.nextLine();
        while (!action.equals("exit")){
            new AppController().execute(action);
            action = sc.nextLine();
        }
    }
}
