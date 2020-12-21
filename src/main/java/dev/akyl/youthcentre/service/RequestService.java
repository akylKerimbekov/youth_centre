package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.Request;
import dev.akyl.youthcentre.repository.entity.Teenager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.List;

public class RequestService {
    private static RequestService instance;
    private ObservableList<Request> requests;

    private RequestService() {
    }

    public static RequestService getInstance(){
        if (instance == null) {
            instance = new RequestService();
        }
        return instance;
    }

    @Transactional
    public ObservableList<Request> findAll(){
        List<Request> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("from Request", Request.class).list();
            students.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        requests = FXCollections.observableArrayList(students);
        return requests;
    }

    @Transactional
    public ObservableList<Request> findByTeenagerId(Long teenagerId){
        List<Request> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("from Request where teenagerId = :teenagerId", Request.class)
                    .setParameter("teenagerId", teenagerId)
                    .list();
            students.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        requests = FXCollections.observableArrayList(students);
        return requests;
    }
}
