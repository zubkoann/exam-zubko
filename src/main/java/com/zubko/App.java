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
                "просмотр списка уже существующих пользователей - 1 \n" +
                "добавление нового пользователя - 2\n" +
                "удаление пользователя - 3\n" +
                "поиск пользователя по id / email / фамилии  - 4\n" +
                "показать список оборудования у пользователя - 5\n" +
                "список всего оборудования - 6\n" +
                "добавление нового оборудования \"на склад\"  - 7 \n" +
                "удаление оборудования (нельзя удалить, если оборудование выдано пользователю) - 8\n" +
                "выдача оборудования пользователю -  9\n" +
                "поиск оборудования по типу / по \"выдано/насклда\" / по пользователю -10 \n" +
                "создание отчета по пользователям - текстовый файл - 11\n" +
                "выход из программы - exit" );
        action = sc.nextLine();
        while (!action.equals("exit")){
            new AppController().execute(action);
            action = sc.nextLine();
        }
    }
}
