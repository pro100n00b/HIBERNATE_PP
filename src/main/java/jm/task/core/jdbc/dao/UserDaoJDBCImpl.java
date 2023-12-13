package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Util con = new Util();
        try (Statement db = con.getConnectionJDBC().createStatement()) {
            db.execute("CREATE TABLE IF NOT EXISTS users ( id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30), lastname VARCHAR(30), age INTEGER(3) )");

        } catch (SQLException e) {

        }

    }

    public void dropUsersTable() {
        Util con = new Util();
        try (Statement db = con.getConnectionJDBC().createStatement()) {
            db.execute("DROP TABLE users");

        } catch (SQLException e) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Util con = new Util();
        try {
            Connection connection = con.getConnectionJDBC();
            PreparedStatement db = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)");
            db.setString(1, name);
            db.setString(2, lastName);
            db.setInt(3, age);
            db.execute();
            System.out.printf("User с именем – %s добавлен в базу данных " + "\n", name);
        } catch (SQLException e) {

        }


    }

    public void removeUserById(long id) {
        Util con = new Util();
        try (Statement db = con.getConnectionJDBC().createStatement()) {
            db.execute("DELETE FROM users WHERE id = " + id);

        } catch (SQLException e) {

        }

    }

    public List<User> getAllUsers() {
        Util con = new Util();
        ArrayList<User> result = new ArrayList<>();
        try (Statement db = con.getConnectionJDBC().createStatement()) {
            ResultSet data = db.executeQuery("SELECT * FROM users");

            while (data.next()) {
                long id = data.getLong(1);
                String n = data.getNString(2);
                String lN = data.getNString(3);
                Byte age = data.getByte(4);
                User you = new User();
                you.setAge(age);
                you.setLastName(lN);
                you.setName(n);
                you.setId(id);
                result.add(you);

            }
            return result;
        } catch (SQLException e) {
            return null;
        }


    }


    public void cleanUsersTable() {
        Util con = new Util();
        try (Statement db = con.getConnectionJDBC().createStatement()) {
            db.execute("TRUNCATE TABLE users");

        } catch (SQLException e) {

        }

    }
}
