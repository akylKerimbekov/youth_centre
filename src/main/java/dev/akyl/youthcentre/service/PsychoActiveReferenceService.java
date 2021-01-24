package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.PsychoActiveRef;
import dev.akyl.youthcentre.repository.entity.Request;
import dev.akyl.youthcentre.repository.entity.Teenager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class PsychoActiveReferenceService {

    private static PsychoActiveReferenceService instance;
    private ObservableList<PsychoActiveRef> psychoActiveReferences;

    private PsychoActiveReferenceService() {

    }

    public static PsychoActiveReferenceService getInstance() {
        if (instance == null) {
            instance = new PsychoActiveReferenceService();
        }
        return instance;
    }

    public ObservableList<PsychoActiveRef> getPsychoActiveReferences() {
        return psychoActiveReferences;
    }

    @Transactional
    public ObservableList<PsychoActiveRef> findAll() {
        List<PsychoActiveRef> psychoActiveRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            psychoActiveRefList = session.createQuery("from PsychoActiveRef", PsychoActiveRef.class).list();
            psychoActiveRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        psychoActiveReferences = FXCollections.observableArrayList(psychoActiveRefList);
        return psychoActiveReferences;
    }

    @Transactional
    public ObservableList<PsychoActiveRef> findById(Long psychoAciveRefId){
        List<PsychoActiveRef> psychoActiveRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            psychoActiveRefList = session.createQuery("from PsychoActiveRef where id = :psychoAciveRefId", PsychoActiveRef.class)
                    .setParameter("psychoAciveRefId", psychoAciveRefId)
                    .list();
            psychoActiveRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        psychoActiveReferences = FXCollections.observableArrayList(psychoActiveRefList);
        return psychoActiveReferences;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public PsychoActiveRef save(PsychoActiveRef psychoActiveRef) {
        System.out.println(psychoActiveRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(psychoActiveRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.persist(psychoActiveRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(psychoActiveRef);
        return psychoActiveRef;
    }

    @Transactional
    public PsychoActiveRef update(PsychoActiveRef psychoActiveRef) {
        System.out.println(psychoActiveRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(psychoActiveRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.saveOrUpdate(psychoActiveRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(psychoActiveRef);
        return psychoActiveRef;
    }
}
