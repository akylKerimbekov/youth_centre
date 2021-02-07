package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.SurveyResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class SurveyResultService {
    private static SurveyResultService instance;
    private ObservableList<SurveyResult> surveyResults;

    private SurveyResultService(){}

    public static SurveyResultService getInstance() {
        if (instance == null) {
            instance = new SurveyResultService();
        }
        return instance;
    }

    public ObservableList<SurveyResult> getSurveyResults() {
        return surveyResults;
    }

    @Transactional
    public ObservableList<SurveyResult> findAll(){
        List<SurveyResult> surveyResultList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            surveyResultList = session.createQuery("from SurveyResult", SurveyResult.class).list();
            surveyResultList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        surveyResults = FXCollections.observableArrayList(surveyResultList);
        return surveyResults;
    }

    @Transactional
    public ObservableList<SurveyResult> findBySurveyResultId(Long surveyResultId){
        List<SurveyResult> surveyResultList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            surveyResultList = session.createQuery("from SurveyResult where id = :surveyResultId", SurveyResult.class)
                    .setParameter("surveyResultId", surveyResultId)
                    .list();
            surveyResultList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        surveyResults = FXCollections.observableArrayList(surveyResultList);
        return surveyResults;
    }

    @Transactional
    public SurveyResult save(SurveyResult surveyResult) {
        System.out.println(surveyResult);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(surveyResult);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.persist(surveyResult);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(surveyResult);
        return surveyResult;
    }

    @Transactional
    public SurveyResult update(SurveyResult surveyResult) {
        System.out.println(surveyResult);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session);
            System.out.println(surveyResult);
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.saveOrUpdate(surveyResult);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(surveyResult);
        return surveyResult;
    }
}
