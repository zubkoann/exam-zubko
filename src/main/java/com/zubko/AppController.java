package com.zubko;

import com.zubko.connectors.UserDatabaseConnector;
import com.zubko.models.User;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class AppController {
    private static Scanner sc = new Scanner(System.in);

    public void execute(String action){
        UserDatabaseConnector db = UserDatabaseConnector.getInstance();

        switch (action) {
            case "listOfUsers":
                List<User> list = db.getAll();
                System.out.println(list);
                break;
            case "insertUser":
                db.insert(getNewUser());
                break;
            case "delete":
                db.delete(getId());
                break;
            default:
                break;
        }

    }

    public static User getNewUser() {
        System.out.println("surname");
        String surname = sc.nextLine();
        System.out.println("name");
        String name = sc.nextLine();
        System.out.println("email");
        String email = sc.nextLine();
        System.out.println("role");
        String role = sc.nextLine();
        System.out.println("team");
        String team = sc.nextLine();
        return new User(surname,name,email,role,team);
    }

    public static int getId() {
        System.out.println("id of user");
        int id = sc.nextInt();
        return id;
    }
}
