package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoHibernateImpl db = new UserDaoHibernateImpl();
        db.createUsersTable();


    }

    public void dropUsersTable() {
        UserDaoHibernateImpl db = new UserDaoHibernateImpl();
        db.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl db = new UserDaoHibernateImpl();
        db.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl db = new UserDaoHibernateImpl();
        db.removeUserById(id);

    }

    public List<User> getAllUsers() {
        UserDaoHibernateImpl db = new UserDaoHibernateImpl();
        return db.getAllUsers();

    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl db = new UserDaoHibernateImpl();
        db.cleanUsersTable();

    }
}
