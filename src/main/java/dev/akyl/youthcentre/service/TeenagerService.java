package dev.akyl.youthcentre.service;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.Teenager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class TeenagerService {
    private static TeenagerService instance;
    private ObservableList<Teenager> teenagers;

    public static TeenagerService getInstance(){
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
    public ObservableList<Teenager> findAll(){
        List<Teenager> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("from Teenager", Teenager.class).list();
            students.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        teenagers = FXCollections.observableArrayList(students);
        return teenagers;
    }

}
