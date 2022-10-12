package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDaoJDBCImpl DAO = new UserDaoJDBCImpl();
    private static final UserDaoHibernateImpl DAOH = new UserDaoHibernateImpl();

    public void createUsersTable() {
        DAOH.createUsersTable();
    }

    public void dropUsersTable() {
        DAOH.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        DAOH.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        DAOH.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return DAOH.getAllUsers();
    }

    public void cleanUsersTable() {
        DAOH.cleanUsersTable();
    }
}
