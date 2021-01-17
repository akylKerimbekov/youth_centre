package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.Teenager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class TeenagerService {
    private static TeenagerService instance;
    private ObservableList<Teenager> teenagers;

    public static TeenagerService getInstance() {
        if (instance == null) {
            instance = new TeenagerService();
        }
        return instance;
    }

    private TeenagerService() {
    }

    public ObservableList<Teenager> getTeenagers() {
        return teenagers;
    }

    @Transactional
    public ObservableList<Teenager> findAll() {
        List<Teenager> teenagerList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teenagerList = session.createQuery("from Teenager", Teenager.class).list();
            teenagerList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        teenagers = FXCollections.observableArrayList(teenagerList);
        return teenagers;
    }

    @Transactional
    public Teenager save(Teenager teenager) {
        System.out.println(teenager);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(teenager);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.persist(teenager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(teenager);
        return teenager;
    }

    @Transactional
    public Teenager update(Teenager teenager) {
        System.out.println(teenager);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(teenager);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.saveOrUpdate(teenager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(teenager);
        return teenager;
    }
}
