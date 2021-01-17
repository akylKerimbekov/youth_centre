package dev.akyl.youthcentre;

import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.repository.entity.Teenager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void generateTeenagers(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("str");
        Teenager student = new Teenager();
        student.setFirstName("Kumar");
        student.setEmail("kumar@email.com");
        Teenager student1 = new Teenager();
        student1.setFirstName("John");
        student1.setEmail("john@email.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(student);
            session.save(student1);
            session.flush();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Teenager> students = session.createQuery("from Teenager", Teenager.class).list();
            students.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
