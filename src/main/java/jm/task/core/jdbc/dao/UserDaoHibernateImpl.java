package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Util ut = new Util();
        Session se = ut.getSessionFactory().getCurrentSession();
        se.getTransaction().begin();
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query = se.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        se.getTransaction().commit();
        se.close();


    }

    @Override
    public void dropUsersTable() {
        Util ut = new Util();
        Session se = ut.getSessionFactory().getCurrentSession();
        se.getTransaction().begin();
        String sql = "DROP TABLE IF EXISTS users";

        Query query = se.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        se.getTransaction().commit();
        se.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util ut = new Util();
        Session se = ut.getSessionFactory().getCurrentSession();
        se.getTransaction().begin();
        se.save(new User(name, lastName,age));
        se.getTransaction().commit();
        se.close();
    }

    @Override
    public void removeUserById(long id) {
        Util ut = new Util();
        Session se = ut.getSessionFactory().getCurrentSession();
        se.getTransaction().begin();
        se.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();;
        se.getTransaction().commit();
        se.close();

    }

    @Override
    public List<User> getAllUsers() {
        Util ut = new Util();
        Session se = ut.getSessionFactory().getCurrentSession();
        se.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = se.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.select(root);

        List<User> resultList = se.createQuery(query).getResultList();
        se.getTransaction().commit();
        se.close();
        return resultList;

    }

    @Override
    public void cleanUsersTable() {
        Util ut = new Util();
        Session se = ut.getSessionFactory().getCurrentSession();
        se.getTransaction().begin();
        try {
            String sql = "TRUNCATE TABLE users";
            Query query = se.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            se.getTransaction().commit();
            se.close();
        }catch (PersistenceException e){

        }





    }
}
