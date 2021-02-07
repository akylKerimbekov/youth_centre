package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.SurveyRef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class SurveyRefService {
    private static SurveyRefService instance;
    private ObservableList<SurveyRef> surveyRefs;

    private SurveyRefService() {}

    public static SurveyRefService getInstance() {
        if (instance == null) {
            instance = new SurveyRefService();
        }
        return instance;
    }

    public ObservableList<SurveyRef> getSurveyRefs() {
        return surveyRefs;
    }

    @Transactional
    public ObservableList<SurveyRef> findAll(){
        List<SurveyRef> surveyRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            surveyRefList = session.createQuery("from SurveyRef", SurveyRef.class).list();
            surveyRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        surveyRefs = FXCollections.observableArrayList(surveyRefList);
        return surveyRefs;
    }

    @Transactional
    public ObservableList<SurveyRef> findBySurveyRefId(Long surveyRefId){
        List<SurveyRef> surveyRefList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            surveyRefList = session.createQuery("from SurveyRef where id = :surveyRefId", SurveyRef.class)
                    .setParameter("surveyRefId", surveyRefId)
                    .list();
            surveyRefList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        surveyRefs = FXCollections.observableArrayList(surveyRefList);
        return surveyRefs;
    }

    @Transactional
    public SurveyRef save(SurveyRef surveyRef) {
        System.out.println(surveyRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(surveyRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.persist(surveyRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(surveyRef);
        return surveyRef;
    }

    @Transactional
    public SurveyRef update(SurveyRef surveyRef) {
        System.out.println(surveyRef);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(surveyRef);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.saveOrUpdate(surveyRef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(surveyRef);
        return surveyRef;
    }
}
