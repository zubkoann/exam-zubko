package com.zubko;

import com.zubko.connectors.UserDatabaseConnector;
import com.zubko.models.User;

import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static String action;

    public static void main(String[] args) {
//        UserDatabaseConnector db = UserDatabaseConnector.getInstance();
//        User newU = new User("Anna", "Zubko", "123@i.ua", "dir", "fintech");
//        db.insert(newU);

        System.out.println("Input Action:" +
                "просмотр списка уже существующих пользователей - 1" +
                "добавление нового пользователя - 2" +
                "удаление пользователя - 3" +
                "поиск пользователя по id / email / фамилии  - 4" +
                "показать список оборудования у пользователя - 5" +
                "список всего оборудования - 6" +
                "добавление нового оборудования \"на склад\"  - 7 " +
                "удаление оборудования (нельзя удалить, если оборудование выдано пользователю) - 8" +
                "выдача оборудования пользователю -  9" +
                "поиск оборудования по типу / по \"выдано/насклда\" / по пользователю -10 " +
                "создание отчета по пользователям - текстовый файл" +
                "выход из программы - exit" );
        action = sc.nextLine();
        while (!action.equals("exit")){
            new AppController().execute(action);
            action = sc.nextLine();
        }
    }
}
