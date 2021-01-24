package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.DeliveryServiceRef;
import dev.akyl.youthcentre.repository.entity.HardLifeRef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class DeliveryServiceRefService {
    private static DeliveryServiceRefService instance;
    private ObservableList<DeliveryServiceRef> deliveryServiceRefObservableList;

    private DeliveryServiceRefService() {
    }

    public static DeliveryServiceRefService getInstance() {
        if (instance == null) {
            instance = new DeliveryServiceRefService();
        }
        return instance;
    }

    public ObservableList<DeliveryServiceRef> getDeliveryServiceRefObservableList() {
        return deliveryServiceRefObservableList;
    }

    @Transactional
    public ObservableList<DeliveryServiceRef> findAll() {
        List<DeliveryServiceRef> deliveryServiceRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            deliveryServiceRefList = session.createQuery("from DeliveryServiceRef", DeliveryServiceRef.class).list();
            deliveryServiceRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        deliveryServiceRefObservableList = FXCollections.observableArrayList(deliveryServiceRefList);
        return deliveryServiceRefObservableList;
    }

    @Transactional
    public ObservableList<DeliveryServiceRef> findById(Long deliveryServiceRefId){
        List<DeliveryServiceRef> deliveryServiceRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            deliveryServiceRefList = session.createQuery("from DeliveryServiceRef where id = :deliveryServiceRefId", DeliveryServiceRef.class)
                    .setParameter("deliveryServiceRefId", deliveryServiceRefId)
                    .list();
            deliveryServiceRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        deliveryServiceRefObservableList = FXCollections.observableArrayList(deliveryServiceRefList);
        return deliveryServiceRefObservableList;
    }

    @Transactional
    public DeliveryServiceRef save(DeliveryServiceRef deliveryServiceRef) {
        System.out.println(deliveryServiceRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(deliveryServiceRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.persist(deliveryServiceRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(deliveryServiceRef);
        return deliveryServiceRef;
    }

    @Transactional
    public DeliveryServiceRef update(DeliveryServiceRef deliveryServiceRef) {
        System.out.println(deliveryServiceRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(deliveryServiceRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.saveOrUpdate(deliveryServiceRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(deliveryServiceRef);
        return deliveryServiceRef;
    }
}
