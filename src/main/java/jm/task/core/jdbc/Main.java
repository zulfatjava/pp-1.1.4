package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        Util.getConnection();

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("User1", "User1", (byte) 11);
        System.out.println("User с именем User1 добавлен в базу данных");

        userService.saveUser("User2", "User2", (byte) 22);
        System.out.println("User с именем User2 добавлен в базу данных");

        userService.saveUser("User3", "User3", (byte) 33);
        System.out.println("User с именем User3 добавлен в базу данных");

        userService.saveUser("User4", "User4", (byte) 44);
        System.out.println("User с именем User4 добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();

        Util.closeConnection();
    }

}

