package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.HardLifeRef;
import dev.akyl.youthcentre.repository.entity.PsychoActiveRef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class HardLifeService {
    private static HardLifeService instance;
    private ObservableList<HardLifeRef> hardLifeRefObservableList;

    private HardLifeService() {
    }

    public static HardLifeService getInstance() {
        if (instance == null) {
            instance = new HardLifeService();
        }
        return instance;
    }

    public ObservableList<HardLifeRef> getHardLifeRefObservableList() {
        return hardLifeRefObservableList;
    }


    @Transactional
    public ObservableList<HardLifeRef> findAll() {
        List<HardLifeRef> hardLifeRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            hardLifeRefList = session.createQuery("from HardLifeRef", HardLifeRef.class).list();
            hardLifeRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hardLifeRefObservableList = FXCollections.observableArrayList(hardLifeRefList);
        return hardLifeRefObservableList;
    }

    @Transactional
    public ObservableList<HardLifeRef> findById(Long hardLifeRefId){
        List<HardLifeRef> hardLifeRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            hardLifeRefList = session.createQuery("from HardLifeRef where id = :hardLifeRefId", HardLifeRef.class)
                    .setParameter("hardLifeRefId", hardLifeRefId)
                    .list();
            hardLifeRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hardLifeRefObservableList = FXCollections.observableArrayList(hardLifeRefList);
        return hardLifeRefObservableList;
    }

    @Transactional
    public HardLifeRef save(HardLifeRef hardLifeRef) {
        System.out.println(hardLifeRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(hardLifeRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.persist(hardLifeRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(hardLifeRef);
        return hardLifeRef;
    }

    @Transactional
    public HardLifeRef update(HardLifeRef hardLifeRef) {
        System.out.println(hardLifeRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(hardLifeRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.saveOrUpdate(hardLifeRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(hardLifeRef);
        return hardLifeRef;
    }
}
