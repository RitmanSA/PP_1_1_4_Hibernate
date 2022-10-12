package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.query.NativeQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sf = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        NativeQuery<?> query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT primary key auto_increment, " +
                "name VARCHAR(15), " +
                "lastName VARCHAR(15)," +
                " age TINYINT);");
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        NativeQuery<?> query = session.createSQLQuery("DROP TABLE IF EXISTS users;");
        query.executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sf.getCurrentSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("User с именем" + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createQuery("FROM User", User.class).list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
    }
}
