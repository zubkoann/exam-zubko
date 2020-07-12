package com.zubko;

import com.zubko.connectors.TechDatabaseConnector;
import com.zubko.connectors.UserDatabaseConnector;
import com.zubko.models.Tech;
import com.zubko.models.TechTypes;
import com.zubko.models.User;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class AppController {
    private static Scanner sc = new Scanner(System.in);
    private List<User> users;
    private List<Tech> tech;
    private UserDatabaseConnector uc = UserDatabaseConnector.getInstance();
    private TechDatabaseConnector tc = TechDatabaseConnector.getInstance();

    public void execute(String action) {


        switch (action) {
            case "1":
                System.out.println(uc.getAll());
                break;
            case "2":
                uc.insert(getNewUser());
                break;
            case "3":
                uc.delete(getId());
                break;
            case "4":
                String param = getSearchParam();
                if (param.equals("id")) {
                    uc.findById(getId());
                } else {
                    uc.findBy(param, getValueOfParam(param));
                }
                break;
            case "5":
                System.out.println(tc.findBy("userId", getId()));
                break;
            case "6":
                System.out.println(tc.getAll());
                break;
            case "7":
                tc.insert(getNewTech());
                break;
            case "8":
                int idTechForDelete = getIdTech();
                Tech techForDelete = tc.findById(idTechForDelete);
                if (techForDelete.getUserId() > 0) {
                    System.out.println("tech has already been sent to user - " + techForDelete.getUserId());
                } else {
                    tc.delete(idTechForDelete);
                }
                break;
            case "9":
                Tech tech = tc.findById(getIdTech());
                if (tech.getUserId() > 0) {
                    System.out.println("tech has already been sent to user - " + tech.getUserId());
                } else {
                    tech.setUserId(getId());
                    tc.update(tech);
                }
                break;
            case "10":
                getActionForSerchTech();
                break;
            case "11":
                writeReportToFile();
                break;
            default:
                break;
        }

    }

    public User getNewUser() {
        System.out.println("surname?");
        String surname = sc.nextLine();
        System.out.println("name?");
        String name = sc.nextLine();
        System.out.println("email?");
        String email = sc.nextLine();
        System.out.println("role?");
        String role = sc.nextLine();
        System.out.println("team?");
        String team = sc.nextLine();
        return new User(surname, name, email, role, team);
    }

    public int getId() {
        System.out.println("id of user?");
        int id = sc.nextInt();
        return id;
    }

    public int getIdTech() {
        System.out.println("id of tech?");
        int id = sc.nextInt();
        return id;
    }

    public String getTypeTech() {
        System.out.println("LAPTOP, TABLET or PHONE?");
        String type = TechTypes.valueOf(sc.nextLine().toUpperCase()).toString();
        return type;
    }

    public String getValueOfParam(String param) {
        System.out.println("Value for search by " + param);
        String v = sc.nextLine();
        return v;
    }

    public String getSearchParam() {
        System.out.println("set search param id  email or surname ");
        String param = sc.nextLine();
        return param;
    }

    public Tech getNewTech() {
        System.out.println(":   LAPTOP, TABLET or PHONE");
        String type = sc.nextLine();
        System.out.println("name?");
        String name = sc.nextLine();
        System.out.println("model?");
        String model = sc.nextLine();
        System.out.println("date?");
        String date = sc.nextLine();
        return new Tech(type, name, model, date);
    }

    public void getActionForSerchTech() {
        System.out.println("Search tech by type-1, store - 2, sent to user - 3, by user id - 4");
        String action = sc.nextLine();
        switch (action) {
            case "1":
                System.out.println(tc.findBy("type", getTypeTech().toUpperCase()));
                break;
            case "2":
                System.out.println(tc.findBy("userId", 0));
                break;
            case "3":
                System.out.println(tc.getAllSentToUser());
                break;
            case "4":
                System.out.println(tc.findById(getId()));
                break;
            default:
                break;
        }
    }
    public void writeReportToFile(){

    }

}
