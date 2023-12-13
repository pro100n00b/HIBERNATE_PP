package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    private final Util ut = new Util();
    public UserDaoHibernateImpl() {

    }




    @Override
    public void createUsersTable() {
        try (Session se = ut.getSessionFactory().openSession()) {
            se.getTransaction().begin();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            Query query = se.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            se.getTransaction().commit();
        } catch (HibernateException ignored) {

        }


    }

    @Override
    public void dropUsersTable() {
        try (Session se = ut.getSessionFactory().openSession()) {
            se.getTransaction().begin();
            String sql = "DROP TABLE IF EXISTS users";
            Query query = se.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            se.getTransaction().commit();
        } catch (HibernateException ignored) {

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session se = ut.getSessionFactory().openSession()) {
            se.getTransaction().begin();
            se.save(new User(name, lastName, age));
            se.getTransaction().commit();
        } catch (HibernateException ignored) {

        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session se = ut.getSessionFactory().openSession()) {
            se.getTransaction().begin();
            se.createQuery("delete from User where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            ;
            se.getTransaction().commit();
        } catch (HibernateException ignored) {

        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session se = ut.getSessionFactory().openSession()) {
            se.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = se.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);

            query.select(root);

            List<User> resultList = se.createQuery(query).getResultList();
            se.getTransaction().commit();
            return resultList;
        } catch (HibernateException ignored) {

        }
        return null;


    }

    @Override
    public void cleanUsersTable() {
        try (Session se = ut.getSessionFactory().openSession()) {

            se.getTransaction().begin();

            String sql = "TRUNCATE TABLE users";
            Query query = se.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            se.getTransaction().commit();
        } catch (HibernateException ignored) {

        }


    }
}
