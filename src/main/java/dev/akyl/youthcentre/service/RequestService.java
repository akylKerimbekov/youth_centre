package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.Request;
import dev.akyl.youthcentre.repository.entity.Teenager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public ObservableList<Request> getRequests() {
        return requests;
    }

    @Transactional
    public ObservableList<Request> findAll(){
        List<Request> requestList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            requestList = session.createQuery("from Request", Request.class).list();
            requestList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        requests = FXCollections.observableArrayList(requestList);
        return requests;
    }

    @Transactional
    public ObservableList<Request> findByTeenagerId(Long teenagerId){
        List<Request> requestList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            requestList = session.createQuery("from Request where teenagerId = :teenagerId", Request.class)
                    .setParameter("teenagerId", teenagerId)
                    .list();
            requestList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        requests = FXCollections.observableArrayList(requestList);
        return requests;
    }

    @Transactional
    public Request save(Request request) {
        System.out.println(request);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(request);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.persist(request);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(request);
        return request;
    }

    @Transactional
    public Request update(Request request) {
        System.out.println(request);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(request);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.saveOrUpdate(request);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(request);
        return request;
    }
}
